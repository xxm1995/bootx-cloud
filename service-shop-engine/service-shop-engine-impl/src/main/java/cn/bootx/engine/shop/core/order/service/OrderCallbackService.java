package cn.bootx.engine.shop.core.order.service;

import cn.bootx.engine.shop.core.pay.service.OrderPayService;
import cn.bootx.engine.shop.mq.MessageSender;
import cn.bootx.order.client.OrderFindClient;
import cn.bootx.order.code.OrderStatusCode;
import cn.bootx.order.dto.order.OrderDto;
import cn.bootx.paymentcenter.dto.pay.PayResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**   
* 订单回调服务类
* @author xxm  
* @date 2021/4/21 
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderCallbackService {

    private final OrderFindClient orderFindClient;
    private final MessageSender paymentEventSender;
    private final OrderPayService payOrderService;

    /**
     * 接受支付完成事件进行处理
     */
    public void callbackProcess(PayResult PayResult){
        String businessId = PayResult.getPayment()
                .getBusinessId();
        OrderDto orderDto = orderFindClient.getWholeById(Long.valueOf(businessId));

        // 已支付 不再进行处理(理论上不会出现,支付中心已经做了控制)
        if (Objects.equals(OrderStatusCode.STATUS_NOT_YET_SHIPPED,orderDto.getStatus())
                ||Objects.equals(OrderStatusCode.STATUS_PAYED,orderDto.getStatus())){
            log.error("已支付重复事件");
            return;
        }

        // 检查订单, 已取消的话发送撤销支付交易单的请求消息
        if (Objects.equals(OrderStatusCode.STATUS_CANCEL,orderDto.getStatus())){
            paymentEventSender.sendCancelPay(PayResult.getPayment().getId());
        }
        else {
            payOrderService.afterPaymentHandler(orderDto);
        }
    }
}
