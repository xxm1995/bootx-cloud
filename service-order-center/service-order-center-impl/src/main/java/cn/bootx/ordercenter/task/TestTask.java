package cn.bootx.ordercenter.task;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
* 测试任务
* @author xxm  
* @date 2021/3/11 
*/
@Slf4j
@Component
public class TestTask {

    @XxlJob("cs-demoJobHandler")
    public ReturnT<String> demoJobHandler(String s) {
        XxlJobHelper.getShardIndex();
        XxlJobHelper.log("This is a demo job.");
        return ReturnT.SUCCESS;
    }
}
