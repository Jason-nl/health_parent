package cn.itcast.health.vo;


import cn.itcast.health.pojo.CheckGroup;
import cn.itcast.health.pojo.Setmeal;
import lombok.Data;

import java.util.List;

@Data
public class SetmealVO extends Setmeal {

    private Integer[] checkgroupIds;
    private List<CheckGroup> checkgroupList;

}
