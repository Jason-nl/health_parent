package cn.itcast.health.mapper;

import cn.itcast.health.pojo.Setmeal;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * created by Ethan on 2021/1/15 9:27 上午
 */
public interface SetmealMapper extends BaseMapper<Setmeal> {

    @Select("select  checkgroup_id from t_setmeal_checkgroup where setmeal_id = #{sid}")
    Integer[] findCheckGroupsBySid(@Param("sid") int id);

    @Delete("delete from t_setmeal_checkgroup where setmeal_id = #{sid}")
    void deleteWithGroupOrItemByGid(@Param("sid") Integer sid);

    @Insert("insert into t_setmeal_checkgroup values(#{sid}, #{gid})")
    void insertGroupAndItem(@Param("sid") Integer sid, @Param("gid") Integer gid);
}
