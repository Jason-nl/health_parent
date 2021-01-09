package cn.itcast.health.vo;


import cn.itcast.health.pojo.Menu;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MenuVO extends Menu {

    private List<MenuVO> children = new ArrayList<MenuVO>();//子菜单集合

}
