package cn.itcast.health.mapper;

import cn.itcast.health.pojo.CheckGroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * created by Ethan on 2021/1/12 1:24 下午
 */
public interface CheckGroupMapper extends BaseMapper<CheckGroup> {
    @Delete("delete from T_CHECKGROUP_CHECKITEM where CHECKGROUP_ID = #{gid}")
    void deleteByGid(@Param("gid") Integer gid);

    @Insert("insert into T_CHECKGROUP_CHECKITEM values(#{gid},#{checkitemIds})")
    void insertGidAndItemIds(@Param("gid") Integer gid, @Param("checkitemIds") Integer checkitemId);

    @Select("select CHECKITEM_ID from T_CHECKGROUP_CHECKITEM where CHECKGROUP_ID = #{gid}")
    int[] selectcheckItemIds(@Param("gid") int id);
}
