package cn.itcast.health.vo;


import cn.itcast.health.pojo.CheckGroup;
import cn.itcast.health.pojo.CheckItem;
import lombok.Data;

import java.util.List;

//  view  object
@Data
public class CheckGroupVO extends CheckGroup {

    private List<CheckItem> checkItems;

    private int[] checkItemIds;

}
