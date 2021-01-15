package cn.itcast.health.service;

import cn.itcast.health.dto.SetmealDTO;
import cn.itcast.health.entity.PageResult;
import cn.itcast.health.entity.QueryPageBean;
import cn.itcast.health.pojo.Setmeal;
import cn.itcast.health.vo.SetmealVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * created by Ethan on 2021/1/15 9:27 上午
 */
public interface SetmealService extends IService<Setmeal> {

    boolean saveOrUpdateSetmeal(SetmealDTO setmealDTO);

    boolean deleteByLogic(int id);

    PageResult<Setmeal> findPage(QueryPageBean pageBean);

    SetmealVO findSetmealInfoById(int id);

    void cleanImg();
}
