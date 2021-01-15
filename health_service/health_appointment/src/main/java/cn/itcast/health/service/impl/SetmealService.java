package cn.itcast.health.service.impl;

import cn.itcast.health.dto.SetmealDTO;
import cn.itcast.health.entity.PageResult;
import cn.itcast.health.entity.QueryPageBean;
import cn.itcast.health.mapper.SetmealMapper;
import cn.itcast.health.pojo.CheckGroup;
import cn.itcast.health.pojo.Setmeal;
import cn.itcast.health.service.CheckGroupService;
import cn.itcast.health.utils.aliyunoss.AliyunUtils;
import cn.itcast.health.utils.redis.RedisUtil;
import cn.itcast.health.utils.resources.RedisConstant;
import cn.itcast.health.vo.SetmealVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * created by Ethan on 2021/1/15 9:28 上午
 */
@Service
@Transactional
public class SetmealService extends ServiceImpl<SetmealMapper, Setmeal> implements cn.itcast.health.service.SetmealService {

    @Autowired
    private CheckGroupService checkGroupService;

    @Override
    public boolean saveOrUpdateSetmeal(SetmealDTO setmealDTO) {
        if(!RedisUtil.existsKey(RedisConstant.SINGLE_PIC + setmealDTO.getImg())){
            throw  new RuntimeException("超时，请重新提交图片！");
        }
        Integer sid = setmealDTO.getId();
        if (sid != null) {
            // update操作 删除关联 delete from t_setmeal_checkgroup where sid = #{sid}
            baseMapper.deleteWithGroupOrItemByGid(sid);
        }
        // insert操作 insert into t_checkgroup values(...)
        boolean flag = saveOrUpdate(setmealDTO);
        sid = setmealDTO.getId();
        Integer[] checkgroupIds = setmealDTO.getCheckgroupIds();
        if (checkgroupIds != null) {
            for (Integer gid : checkgroupIds) {
                // update 新增关联 insert into t_setmeal_checkgroup values(sid, gid)
                baseMapper.insertGroupAndItem(sid, gid);
            }
        }
        // 清除Redis的有效图片信息，剩下垃圾图片
        System.out.println(setmealDTO.getImg());
        RedisUtil.removeSetMember(RedisConstant.ALL_SETMEAL_PIC_SET, setmealDTO.getImg());
        return flag;
    }

    @Override
    public boolean deleteByLogic(int id) {
        Setmeal setmeal = getById(id);
        setmeal.setIs_delete(1);
        return updateById(setmeal);
    }

    @Override
    public PageResult<Setmeal> findPage(QueryPageBean pageBean) {
        PageResult<Setmeal> pageResult = null;
        QueryWrapper<Setmeal> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_delete", 0); // 查询有效数据
        Page<Setmeal> pageParam = new Page<>(pageBean.getCurrentPage(), pageBean.getPageSize(), true);
        if (StringUtils.isNotBlank(pageBean.getQueryString())) {
            // 模糊查询 助记码
            queryWrapper
                    .like("helpCode", pageBean.getQueryString())
                    .or()
                    .like("name", pageBean.getQueryString());
            Page<Setmeal> page = page(pageParam, queryWrapper);

            long total = page.getTotal();
            List<Setmeal> records = page.getRecords();
            pageResult = new PageResult<>(total, records);
        } else {
            // 无条件查询
            Page<Setmeal> page = page(pageParam, queryWrapper);

            long total = page.getTotal();
            List<Setmeal> records = page.getRecords();
            pageResult = new PageResult<>(total, records);
        }

        return pageResult;
    }

    @Override
    public SetmealVO findSetmealInfoById(int id) {
        List<CheckGroup> checkGroupList = checkGroupService.list();
        Integer[] checkgroupIds = baseMapper.findCheckGroupsBySid(id);
        SetmealVO setmealVO = new SetmealVO();
        setmealVO.setCheckgroupIds(checkgroupIds);
        setmealVO.setCheckgroupList(checkGroupList);
        return setmealVO;
    }

    @Override
    public void cleanImg() {
        // 清理set里面的垃圾图片
        Set<String> members = RedisUtil.getMembersOfSet(RedisConstant.ALL_SETMEAL_PIC_SET);
        for (String uuidFileName : members) {
            String existName = RedisConstant.SINGLE_PIC + uuidFileName;
            if (!RedisUtil.existsKey(existName)) {
                //删除阿里云的过期图片
                AliyunUtils.deleteFile(uuidFileName);
                RedisUtil.removeSetMember(RedisConstant.ALL_SETMEAL_PIC_SET, uuidFileName);
                System.out.println("----垃圾图片清理了一次----" + uuidFileName);
            }
        }
    }


}
