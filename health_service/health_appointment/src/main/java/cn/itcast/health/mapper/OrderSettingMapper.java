package cn.itcast.health.mapper;

import cn.itcast.health.pojo.OrderSetting;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * created by Ethan on 2021/1/15 8:25 下午
 */
public interface OrderSettingMapper extends BaseMapper<OrderSetting> {

    @Select("select id from t_ordersetting where orderdate = #{orderDate}")
    OrderSetting findOrderSettingByOrderDate(@Param("orderDate") Date orderDate);

    @Select("select id,orderdate,number,reservations from t_ordersetting where orderdate between #{beginDate} and #{endDate}")
    List<OrderSetting> findSettingListByDate(@Param("beginDate") String beginDate, @Param("endDate") String endDate);

}
