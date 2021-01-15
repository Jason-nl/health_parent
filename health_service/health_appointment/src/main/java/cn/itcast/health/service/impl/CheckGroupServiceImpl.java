package cn.itcast.health.service.impl;

import cn.itcast.health.dto.CheckGroupDTO;
import cn.itcast.health.entity.PageResult;
import cn.itcast.health.entity.QueryPageBean;
import cn.itcast.health.mapper.CheckGroupMapper;
import cn.itcast.health.pojo.CheckGroup;
import cn.itcast.health.pojo.CheckItem;
import cn.itcast.health.service.CheckGroupService;
import cn.itcast.health.service.CheckItemService;
import cn.itcast.health.vo.CheckGroupVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * created by Ethan on 2021/1/12 1:23 下午
 */
@Service
@Transactional
public class CheckGroupServiceImpl extends ServiceImpl<CheckGroupMapper, CheckGroup> implements CheckGroupService {

    @Autowired
    private CheckItemService checkItemService;

    @Override
    public PageResult<CheckGroup> findPage(QueryPageBean pageBean) {
        PageResult<CheckGroup> pageResult = null;
        QueryWrapper<CheckGroup> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_delete", 0); // 查询有效数据
        Page<CheckGroup> pageParam = new Page<>(pageBean.getCurrentPage(), pageBean.getPageSize(), true);
        if (StringUtils.isNotBlank(pageBean.getQueryString())) {
            // 模糊查询 助记码
            queryWrapper.like("helpCode", pageBean.getQueryString());
            Page<CheckGroup> page = page(pageParam, queryWrapper);
            long total = page.getTotal();
            List<CheckGroup> records = page.getRecords();
            pageResult = new PageResult<>(total, records);
        } else {
            // 无条件查询
            Page<CheckGroup> page = page(pageParam, queryWrapper);
            long total = page.getTotal();
            List<CheckGroup> records = page.getRecords();
            pageResult = new PageResult<>(total, records);
        }

        return pageResult;
    }

    @Override
    public boolean deleteByLogic(int id) {
        // 逻辑删除
        CheckGroup checkGroup = getById(id);
        checkGroup.setIs_delete(1);
        return updateById(checkGroup);
    }

    @Override
    public boolean saveOrUpdateGroup(CheckGroupDTO checkGroupDTO) {
        Integer gid = checkGroupDTO.getId();
        if (gid != null) {
            // update操作 删除中间表 = gid的数据 delete from T_CHECKGROUP_CHECKITEM where CHECKGROUP_ID = #{gid}
            baseMapper.deleteByGid(gid);
        }

        // insert操作
        boolean flag = saveOrUpdate(checkGroupDTO);

        gid = checkGroupDTO.getId();
        Integer[] checkitemIds = checkGroupDTO.getCheckitemIds();
        if (checkitemIds != null) {
            for (Integer checkitemId : checkitemIds) {
                // 录入中间表数据 insert into T_CHECKGROUP_CHECKITEM values(#{gid},#{checkitemIds})
                baseMapper.insertGidAndItemIds(gid, checkitemId);
            }
        }

        return flag;
    }

    @Override
    public CheckGroupVO findGroupInfoById(int id) {
        // 根据gid查询关联表中所有的itemId
        int[] checkItemIds = baseMapper.selectcheckItemIds(id);
        // 查询所有的检查项
        List<CheckItem> checkItemList = checkItemService.list();
        CheckGroupVO checkGroupVO = new CheckGroupVO();
        checkGroupVO.setCheckItems(checkItemList);
        checkGroupVO.setCheckItemIds(checkItemIds);
        return checkGroupVO;
    }
}
