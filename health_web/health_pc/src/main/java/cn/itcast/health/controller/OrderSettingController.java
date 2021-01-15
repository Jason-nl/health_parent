package cn.itcast.health.controller;

import cn.itcast.health.entity.Result;
import cn.itcast.health.service.OrderSettingService;
import cn.itcast.health.utils.poi.POIUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * created by Ethan on 2021/1/15 8:23 下午
 */
@RestController
@RequestMapping("ordersetting")
@Api(tags = "传智健康-预约设置")
public class OrderSettingController {

    @Reference
    private OrderSettingService orderSettingService;

    @ApiOperation(value = "上传功能",notes = "录入Excel的数据到数据库")
    @PostMapping("importOrderSettings")
    public Result importOrderSettings(@RequestParam("excelFile") MultipartFile file) {
        List<String[]> readExcel = POIUtils.readExcel(file);
        return new Result(orderSettingService.importOrderSettings(readExcel));
    }

    @ApiOperation(value = "查询功能",notes = "根据年月查询当月的预约数据")
    @GetMapping("findSettingData/{year}/{month}")
    public Result findSettingData(@PathVariable("year") int year, @PathVariable("month") int month) {
        return new Result(orderSettingService.findSettingData(year, month));
    }

    @ApiOperation(value = "修改功能",notes = "根据预约日期修改预约总人数,排班...")
    @PutMapping("updateNumberAndOrderDate/{number}/{orderDate}")
    public Result updateNumberAndOrderDate(@PathVariable("number") int number, @PathVariable("orderDate") String orderDate) {
        return new Result(orderSettingService.updateNumberAndOrderDate(number, orderDate));
    }
}
