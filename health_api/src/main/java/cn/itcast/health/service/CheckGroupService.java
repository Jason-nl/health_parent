package cn.itcast.health.service;

import cn.itcast.health.dto.CheckGroupDTO;
import cn.itcast.health.entity.PageResult;
import cn.itcast.health.entity.QueryPageBean;
import cn.itcast.health.pojo.CheckGroup;
import cn.itcast.health.vo.CheckGroupVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * created by Ethan on 2021/1/12 1:23 下午
 */
public interface CheckGroupService extends IService<CheckGroup> {
    PageResult<CheckGroup> findPage(QueryPageBean pageBean);

    boolean deleteByLogic(int id);

    boolean saveOrUpdateGroup(CheckGroupDTO checkGroupDTO);

    CheckGroupVO findGroupInfoById(int id);
}
