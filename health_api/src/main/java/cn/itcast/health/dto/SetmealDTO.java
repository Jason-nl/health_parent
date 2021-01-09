package cn.itcast.health.dto;

import cn.itcast.health.pojo.Setmeal;
import lombok.Data;

import java.io.Serializable;

@Data
public class SetmealDTO extends Setmeal implements Serializable {

    private Integer[] checkgroupIds;

}
