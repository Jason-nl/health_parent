package cn.itcast.health.controller;

import cn.itcast.health.entity.PageResult;
import cn.itcast.health.entity.QueryPageBean;
import cn.itcast.health.entity.Result;
import cn.itcast.health.pojo.CheckItem;
import cn.itcast.health.service.CheckItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

/**
 * created by Ethan on 2021/1/9 1:45 下午
 */
@RestController
@RequestMapping("checkitem")
@Api(tags = "传智健康-检查项管理")
public class CheckItemController {

    @Reference
    private CheckItemService checkItemService;

    @ApiOperation(value = "分页功能", notes = "分页查询")
    @PostMapping("/page")
    public Result findPage(@RequestBody QueryPageBean pageBean) {
        PageResult<CheckItem> pageResult = checkItemService.findPage(pageBean);
        return new Result(pageResult);
    }

    @ApiOperation(value = "新增或更新功能",notes = "新增/更新一个检查项")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody CheckItem checkItem) {
        boolean flag = checkItemService.saveOrUpdate(checkItem);
        return new Result(flag);
    }

    @ApiOperation(value = "删除功能", notes = "根据id删除指定的检查项")
    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable("id") int id) {
        // 逻辑删除
        boolean flag = checkItemService.deleteByLogic(id);
        return new Result(flag);
    }
}
