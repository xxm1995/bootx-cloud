package cn.bootx.engine.shop.mq;

import cn.bootx.goods.event.GoodsEventSender;
import cn.bootx.goods.param.inventory.ReduceInventoryParam;
import cn.bootx.goods.param.inventory.UnlockInventoryParam;
import cn.bootx.ordercenter.event.OrderEventSender;
import cn.bootx.paymentcenter.event.PaymentEventSender;
import cn.bootx.salescenter.event.SalesEventSender;
import cn.bootx.salescenter.param.coupon.UseCouponParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 支付中心发送
 * @author xxm
 * @date 2021/4/22
 */
@Slf4j
@RequiredArgsConstructor
@Component
@Import({PaymentEventSender.class, GoodsEventSender.class,OrderEventSender.class, SalesEventSender.class})
public class MessageSender {

    private final PaymentEventSender paymentEventSender;
    private final GoodsEventSender goodsEventSender;
    private final OrderEventSender orderEventSender;
    private final SalesEventSender salesEventSender;

    /**
     * 发送支付撤销事件
     */
    public void sendCancelPay(Long paymentId){
        paymentEventSender.sendCancelPay(paymentId);
    }

    /**
     * 发送解锁库存事件
     */
    public void sendUnlockInventory(UnlockInventoryParam param){
        goodsEventSender.sendUnlockInventory(param);
    }

    /**
     * 发送解锁库存事件
     */
    public void sendReduceInventory(ReduceInventoryParam param){
        goodsEventSender.sendReduceInventory(param);
    }

    /**
     * 发送取消订单状态信息
     */
    public void sendCancelOrder(Long orderId){
        orderEventSender.sendCancelOrder(orderId);
    }

    /**
     * 发送优惠券解除锁定事件
     */
    public void sendUnlockCoupon(List<Long> couponIds){
        salesEventSender.sendUnlockCoupon(Arrays.asList(1L,2L));
    }

    /**
     * 发送优惠券使用事件
     */
    public void sendUseCoupon(UseCouponParam param){
        salesEventSender.sendUseCoupon(param);
    }


}
