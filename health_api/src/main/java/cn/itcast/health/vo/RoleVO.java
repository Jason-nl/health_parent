package cn.itcast.health.vo;


import cn.itcast.health.pojo.Permission;
import cn.itcast.health.pojo.Role;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RoleVO extends Role {

    private List<Permission> permissionsList = new ArrayList<>(0);//对应权限集合
    private List<MenuVO> menuVOList = new ArrayList<>();
}
