package cn.bootx.payment.mq;

import cn.bootx.payment.core.pay.service.PayCancelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static cn.bootx.payment.event.PaymentEventCode.PAY_CANCEL;

/**   
* 消息接收
* @author xxm  
* @date 2021/4/22 
*/
@Slf4j
@Component
@RequiredArgsConstructor
public class MessageListener {
    private final PayCancelService payCancelService;

    /**
     * 支付撤销事件处理
     */
    @RabbitListener(queues = PAY_CANCEL)
    public void payCancel(String businessId) {
        log.info("支付撤销事件:{}",businessId);
        payCancelService.cancelByBusinessId(businessId);
    }
}
