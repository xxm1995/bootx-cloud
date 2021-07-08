package cn.bootx.engine.shop.mq;

import cn.bootx.engine.shop.core.order.service.OrderCallbackService;
import cn.bootx.paymentcenter.dto.pay.PayResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static cn.bootx.engine.shop.config.RabbitMqConfig.PAY_COMPLETED_ENGINE;

/**   
* 接受消息接收
* @author xxm  
* @date 2021/4/22 
*/
@Slf4j
@Component
@RequiredArgsConstructor
public class MessageListener {

    private final OrderCallbackService orderCallbackService;

    /**
     * 支付完成事件处理
     */
    @RabbitListener(queues = PAY_COMPLETED_ENGINE)
    public void paymentCompleted(PayResult PayResult) {
        log.info("处理订单支付完成事件");
        orderCallbackService.callbackProcess(PayResult);
    }
}
