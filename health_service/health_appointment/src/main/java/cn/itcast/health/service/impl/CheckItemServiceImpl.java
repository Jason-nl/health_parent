package cn.itcast.health.service.impl;

import cn.itcast.health.mapper.CheckItemMapper;
import cn.itcast.health.pojo.CheckItem;
import cn.itcast.health.service.CheckItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.Service;

/**
 * @Author Created By Ethan
 * @Date Created on 2021/1/8 19:47
 * @Description
 */
@Service
public class CheckItemServiceImpl extends ServiceImpl<CheckItemMapper,CheckItem> implements CheckItemService {
}
