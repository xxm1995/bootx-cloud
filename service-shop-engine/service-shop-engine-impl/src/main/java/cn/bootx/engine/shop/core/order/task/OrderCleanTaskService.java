package cn.bootx.engine.shop.core.order.task;

import cn.bootx.engine.shop.core.order.dao.OrderCacheManager;
import cn.bootx.engine.shop.mq.MessageSender;
import cn.hutool.core.collection.CollUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author xxm
 * @date 2021/4/16
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderCleanTaskService {

    private final OrderCacheManager orderCacheManager;
    private final MessageSender messageSender;

    /**
     * 定时任务：查询失效的订单,并发送撤销支付单消息
     */
    public void cleanOrderTask() {
        //获取失效的订单列表
        List<Long> orderIds = orderCacheManager.retrieveExpiredOrders();
        if (!CollUtil.isEmpty(orderIds)) {
            for (Long orderId : orderIds) {
                // 发送撤销支付信息
                messageSender.sendCancelPay(orderId);
                // 发送取消订单信息
                messageSender.sendCancelOrder(orderId);
                // 从订单过期时间列表中删除
                orderCacheManager.removeExpiredOrder(orderId);
            }
        }
    }

}
