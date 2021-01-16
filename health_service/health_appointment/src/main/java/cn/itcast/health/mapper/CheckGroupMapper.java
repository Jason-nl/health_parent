package cn.itcast.health.mapper;

import cn.itcast.health.pojo.CheckGroup;
import cn.itcast.health.vo.CheckGroupVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

    @Select("select id,name,remark from T_SETMEAL_CHECKGROUP tsc, T_CHECKGROUP tc where tsc.CHECKGROUP_ID = tc.id and tsc.SETMEAL_ID = #{sid}")
    List<CheckGroupVO> findCheckGroupVoById(@Param("sid") int sid);
}
