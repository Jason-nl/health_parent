package cn.itcast.health.service;

import cn.itcast.health.pojo.OrderSetting;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * created by Ethan on 2021/1/15 8:25 下午
 */
public interface OrderSettingService extends IService<OrderSetting> {
    boolean importOrderSettings(List<String[]> readExcel);

    Map findSettingData(int year, int month);

    boolean updateNumberAndOrderDate(int number, String orderDate);
}
