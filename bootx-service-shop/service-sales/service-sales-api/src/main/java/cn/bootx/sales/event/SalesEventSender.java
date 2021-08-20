package cn.bootx.sales.event;

import cn.bootx.sales.param.coupon.UseCouponParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.List;

/**
 * 销售中心发送器
 * @author xxm
 * @date 2021/4/29
 */
@Slf4j
@RequiredArgsConstructor
public class SalesEventSender {
    private final RabbitTemplate rabbitTemplate;

    /**
     * 发送优惠券使用事件
     */
    public void sendUseCoupon(UseCouponParam param){
        rabbitTemplate.convertAndSend(
                SalesEventCode.EXCHANGE_SALES,
                SalesEventCode.USE_COUPON,
                param
        );
    }

    /**
     * 发送优惠券解除锁定事件
     */
    public void sendUnlockCoupon(List<Long> couponIds){
        rabbitTemplate.convertAndSend(
                SalesEventCode.EXCHANGE_SALES,
                SalesEventCode.UNLOCK_COUPON,
                couponIds
        );
    }

}
