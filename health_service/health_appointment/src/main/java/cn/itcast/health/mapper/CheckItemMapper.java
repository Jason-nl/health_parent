package cn.itcast.health.mapper;

import cn.itcast.health.pojo.CheckItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author Created By Ethan
 * @Date Created on 2021/1/8 19:48
 * @Description
 */
public interface CheckItemMapper extends BaseMapper<CheckItem> {
    @Select("select tc.name from T_CHECKITEM tc, T_CHECKGROUP_CHECKITEM tcc where tcc.CHECKITEM_ID = tc.id and tcc.CHECKGROUP_ID = #{gid}")
    List<CheckItem> findCheckItemListByGid(@Param("gid") Integer gid);

}
