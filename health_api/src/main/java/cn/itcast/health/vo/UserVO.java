package cn.itcast.health.vo;


import cn.itcast.health.pojo.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserVO extends User {

    private List<RoleVO> roleVOList = new ArrayList<>(0);//对应角色集合

}
