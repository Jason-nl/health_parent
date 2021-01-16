package cn.itcast.health.controller;

import cn.itcast.health.entity.Result;
import cn.itcast.health.service.SetmealService;
import cn.itcast.health.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;


/**
 * created by Ethan on 2021/1/15 9:26 上午
 */
@RestController
@RequestMapping("setmeal")
@Api(tags = "传智健康-h5套餐管理")
public class SetmealController {

    @Reference
    private SetmealService setmealService;

    @ApiOperation(value = "查询功能",notes = "查询所有的套餐信息")
    @GetMapping("findAll")
    public Result findAll() {
        return new Result(setmealService.list());
    }

    @ApiOperation(value = "查询功能", notes = "查询指定ID的套餐详情")
    @GetMapping("findSetMealDetail/{id}")
    public Result findSetMealDetail(@PathVariable("id") int id) {
        SetmealVO setmealVO = setmealService.findSetMealDetail(id);
        return new Result(setmealVO);
    }



}
