package cn.itcast.health.controller;

import cn.itcast.health.dto.SetmealDTO;
import cn.itcast.health.entity.PageResult;
import cn.itcast.health.entity.QueryPageBean;
import cn.itcast.health.entity.Result;
import cn.itcast.health.pojo.Setmeal;
import cn.itcast.health.service.SetmealService;
import cn.itcast.health.utils.aliyunoss.AliyunUtils;
import cn.itcast.health.utils.redis.RedisUtil;
import cn.itcast.health.utils.resources.RedisConstant;
import cn.itcast.health.utils.resources.UploadUtils;
import cn.itcast.health.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * created by Ethan on 2021/1/15 9:26 上午
 */
@RestController
@RequestMapping("setmeal")
@Api(tags = "传智健康-套餐管理")
public class SetmealController {

    @Reference
    private SetmealService setmealService;

    @ApiOperation(value = "上传功能",notes = "图片上传")
    @PostMapping("upload")
    public Result upload(@RequestParam("imgFile") MultipartFile file) throws IOException {
        String uuidFileName = null;
        String originalFilename = file.getOriginalFilename();
        if (originalFilename != null) {
            uuidFileName = UploadUtils.generateRandonFileName(originalFilename);
            // 上传文件到oss
            AliyunUtils.uploadMultiPartFileToAliyun(file.getBytes(), uuidFileName);
            // 同时在Redis存一份
            RedisUtil.addToSet(RedisConstant.ALL_SETMEAL_PIC_SET,uuidFileName);
            RedisUtil.set(RedisConstant.SINGLE_PIC+uuidFileName,uuidFileName,100,TimeUnit.SECONDS);
        }
        // 数据回显
        return new Result(uuidFileName);
    }

    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody SetmealDTO setmealDTO) {
        boolean flag = setmealService.saveOrUpdateSetmeal(setmealDTO);
        return new Result(flag);
    }

    @ApiOperation(value = "删除功能", notes = "根据id删除指定的套餐")
    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable("id") int id) {
        //  逻辑删除
        boolean flag = setmealService.deleteByLogic(id);
        return new Result(flag);
    }

    @ApiOperation(value = "分页功能", notes = "分页查询")
    @PostMapping("page")
    public Result findPage(@RequestBody QueryPageBean pageBean) {
        PageResult<Setmeal> pageResult = setmealService.findPage(pageBean);
        return new Result(pageResult);
    }

    @ApiOperation(value = "回显功能",notes = "回显指定的套餐信息")
    @GetMapping("findSetmealInfoById/{id}")
    public Result findSetmealInfoById(@PathVariable("id") int id) {
        return new Result(setmealService.findSetmealInfoById(id));
    }

}
