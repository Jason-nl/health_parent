package cn.itcast.health.service.impl;

import cn.itcast.health.entity.PageResult;
import cn.itcast.health.entity.QueryPageBean;
import cn.itcast.health.mapper.CheckItemMapper;
import cn.itcast.health.pojo.CheckItem;
import cn.itcast.health.service.CheckItemService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;

import java.util.List;

/**
 * @Author Created By Ethan
 * @Date Created on 2021/1/8 19:47
 * @Description
 */
@Service
public class CheckItemServiceImpl extends ServiceImpl<CheckItemMapper,CheckItem> implements CheckItemService {
    @Override
    public PageResult<CheckItem> findPage(QueryPageBean pageBean) {
        PageResult<CheckItem> pageResult = null;
        QueryWrapper<CheckItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_delete", 0); // 查询有效数据
        Page<CheckItem> pageParam = new Page<>(pageBean.getCurrentPage(), pageBean.getPageSize(), true);
        if (StringUtils.isNotBlank(pageBean.getQueryString())) {
            // 模糊查询
            queryWrapper.like("name", pageBean.getQueryString());
            Page<CheckItem> page = page(pageParam, queryWrapper);
            long total = page.getTotal();
            List<CheckItem> records = page.getRecords();
            pageResult = new PageResult<>(total, records);
        } else {
            // 无条件查询
            Page<CheckItem> page = page(pageParam, queryWrapper);
            long total = page.getTotal();
            List<CheckItem> records = page.getRecords();
            pageResult = new PageResult<>(total, records);
        }

        return pageResult;
    }

    @Override
    public boolean deleteByLogic(int id) {
        CheckItem checkItem = getById(id);
        checkItem.setIs_delete(1);
        return updateById(checkItem);
    }
}
