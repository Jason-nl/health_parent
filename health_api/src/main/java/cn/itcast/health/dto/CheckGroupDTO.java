package cn.itcast.health.dto;


import cn.itcast.health.pojo.CheckGroup;
import lombok.Data;

@Data
public class CheckGroupDTO extends CheckGroup {

    /**
     * 选择的检查项id列表
     * */
    private Integer[] checkitemIds;

}
