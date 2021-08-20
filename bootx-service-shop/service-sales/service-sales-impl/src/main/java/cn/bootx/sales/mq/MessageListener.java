package cn.bootx.sales.mq;

import cn.bootx.sales.core.coupon.service.CouponService;
import cn.bootx.sales.param.coupon.UseCouponParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

import static cn.bootx.sales.event.SalesEventCode.UNLOCK_COUPON;
import static cn.bootx.sales.event.SalesEventCode.USE_COUPON;

/**   
* 支付中心接收
* @author xxm  
* @date 2021/4/22 
*/
@Slf4j
@Component
@RequiredArgsConstructor
public class MessageListener {
    private final CouponService couponService;

    /**
     * 取消锁定优惠券
     */
    @RabbitListener(queues = UNLOCK_COUPON)
    public void unlockCoupon(List<Long> couponIds){
        couponService.unlockByIds(couponIds);
    }

    /**
     * 使用优惠券
     */
    @RabbitListener(queues = USE_COUPON)
    public void useCoupon(UseCouponParam param){
        couponService.useBatch(param.getCouponIds(),param.getOrderId());
    }
}
