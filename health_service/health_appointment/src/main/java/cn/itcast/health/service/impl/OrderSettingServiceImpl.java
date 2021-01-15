package cn.itcast.health.service.impl;

import cn.itcast.health.mapper.OrderSettingMapper;
import cn.itcast.health.pojo.OrderSetting;
import cn.itcast.health.service.OrderSettingService;
import cn.itcast.health.utils.date.DateUtils;
import cn.itcast.health.utils.valid.ValidationUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * created by Ethan on 2021/1/15 8:26 下午
 */
@Service
@Transactional
public class OrderSettingServiceImpl extends ServiceImpl<OrderSettingMapper, OrderSetting> implements OrderSettingService {

    @Override
    public boolean importOrderSettings(List<String[]> readExcel) {

        // 将Excel数据批量录入到表中 t_ordersetting
        List<OrderSetting> orderSettingList = convertList(readExcel);
        //根据日期查找有无重复
        for (OrderSetting orderSetting : orderSettingList) {
            Date orderDate = orderSetting.getOrderDate();
            OrderSetting isOrderSettingExist = baseMapper.findOrderSettingByOrderDate(orderDate);
            if (isOrderSettingExist != null) {
                orderSetting.setId(isOrderSettingExist.getId());
            }
        }
        return saveOrUpdateBatch(orderSettingList,100);
    }

    @Override
    public Map findSettingData(int year, int month) {
        String beginDate = String.format("%s-%s-1", year, month);
        Date lastDayOfYearAndMonth = DateUtils.getLastDayOfYearAndMonth(year, month);
        String endDate = DateUtils.parseDate2String(lastDayOfYearAndMonth);
        List<OrderSetting> orderSettingList = baseMapper.findSettingListByDate(beginDate, endDate);
        Map map = convertMap(orderSettingList);
        return map;
    }

    @Override
    public boolean updateNumberAndOrderDate(int number, String orderDate) {
        // 更新数据
        UpdateWrapper<OrderSetting> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("NUMBER", number).eq("ORDERDATE", orderDate);
        return update(updateWrapper);
    }

    private Map convertMap(List<OrderSetting> orderSettingList) {
        Map<String, OrderSetting> orderSettingMap = new HashMap<>();
        if (!ValidationUtil.isEmpty(orderSettingList)) {
            for (OrderSetting orderSetting : orderSettingList) {
                Date orderDate = orderSetting.getOrderDate();
                String date2String = DateUtils.parseDate2String(orderDate,"yyyy-MM-dd");
                orderSettingMap.put(date2String, orderSetting);
            }
        }
        return orderSettingMap;
    }

    private List<OrderSetting> convertList(List<String[]> readExcel) {
        List<OrderSetting> orderSettingList = new ArrayList<>();
        if (readExcel != null && readExcel.size() != 0) {
            for (String[] excel : readExcel) {
                OrderSetting orderSetting = new OrderSetting();
                orderSetting.setReservations(0);
                orderSetting.setNumber(Integer.parseInt(excel[1]));
                orderSetting.setOrderDate(DateUtils.parseString2Date(excel[0],"yyyy/MM/dd"));
                orderSettingList.add(orderSetting);
            }
        }
        return orderSettingList;
    }
}
