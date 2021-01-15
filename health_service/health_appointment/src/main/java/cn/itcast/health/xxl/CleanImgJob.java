package cn.itcast.health.xxl;

import cn.itcast.health.service.SetmealService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * created by Ethan on 2021/1/15 11:18 上午
 */
@JobHandler(value = "health_clean_img")
@Component
public class CleanImgJob extends IJobHandler {

    @Resource
    SetmealService setmealService;

    @Override
    public ReturnT<String> execute(String s) throws Exception {
        setmealService.cleanImg();
        return SUCCESS;
    }
}
