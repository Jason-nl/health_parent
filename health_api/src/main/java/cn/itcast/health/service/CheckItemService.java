package cn.itcast.health.service;

import cn.itcast.health.entity.PageResult;
import cn.itcast.health.entity.QueryPageBean;
import cn.itcast.health.pojo.CheckItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author Created By Ethan
 * @Date Created on 2021/1/8 19:46
 * @Description
 */
public interface CheckItemService extends IService<CheckItem> {
    PageResult<CheckItem> findPage(QueryPageBean pageBean);

    boolean deleteByLogic(int id);

    List<CheckItem> findCheckItemListByGid(Integer id);
}
