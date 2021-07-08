package cn.bootx.engine.shop.task;

import cn.bootx.engine.shop.core.order.task.OrderCleanTaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 订单清理任务
 * @author xxm
 * @date 2021/4/16
 */
@EnableScheduling
@Component
@Slf4j
@RequiredArgsConstructor
public class OrderCleanTask {
    private final OrderCleanTaskService orderCleanTaskService;

    /**
     * 修改失效订单状态，并取消预占的库存
     */
    @Scheduled(fixedDelay = 60*1000)
    public void cleanOrder(){
        orderCleanTaskService.cleanOrderTask();
    }
}
