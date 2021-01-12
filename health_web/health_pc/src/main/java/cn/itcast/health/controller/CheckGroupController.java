package cn.itcast.health.controller;

import cn.itcast.health.dto.CheckGroupDTO;
import cn.itcast.health.entity.PageResult;
import cn.itcast.health.entity.QueryPageBean;
import cn.itcast.health.entity.Result;
import cn.itcast.health.pojo.CheckGroup;
import cn.itcast.health.service.CheckGroupService;
import cn.itcast.health.vo.CheckGroupVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

/**
 * created by Ethan on 2021/1/9 1:45 下午
 */
@RestController
@RequestMapping("checkgroup")
@Api(tags = "传智健康-检查组管理")
public class CheckGroupController {

    @Reference
    private CheckGroupService checkGroupService;

    @ApiOperation(value = "分页功能", notes = "分页查询")
    @PostMapping("/page")
    public Result findPage(@RequestBody QueryPageBean pageBean) {
        PageResult<CheckGroup> pageResult = checkGroupService.findPage(pageBean);
        return new Result(pageResult);
    }

    @ApiOperation(value = "新增或更新功能", notes = "新增/更新一个检查组")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody CheckGroupDTO checkGroupDTO) {
        boolean flag = checkGroupService.saveOrUpdateGroup(checkGroupDTO);
        return new Result(flag);
    }

    @ApiOperation(value = "删除功能", notes = "根据id删除指定的检查组")
    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable("id") int id) {
        // 逻辑删除
        boolean flag = checkGroupService.deleteByLogic(id);
        return new Result(flag);
    }

    @ApiOperation(value = "数据回显", notes = "回显检查组的信息")
    @GetMapping("findGroupInfoById/{id}")
    public Result findGroupInfoById(@PathVariable("id") int id) {
        CheckGroupVO checkGroupVO = checkGroupService.findGroupInfoById(id);
        return new Result(checkGroupVO);
    }
}
