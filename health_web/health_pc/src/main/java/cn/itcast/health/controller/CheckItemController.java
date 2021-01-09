package cn.itcast.health.controller;

import cn.itcast.health.entity.Result;
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

    @ApiOperation(value = "查询功能", notes = "检查项查询列表信息")
    @GetMapping("findAll")
    public Result findAll() {
        return new Result(checkItemService.list());
    }

    @ApiOperation(value = "删除功能",notes = "根据ID删除指定的检查项")
    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable("id") int id) {
        return new Result(checkItemService.removeById(id));
    }
}
