package cn.bootx.ordercenter.client;


import cn.bootx.ordercenter.dto.order.OrderDto;
import cn.bootx.ordercenter.param.order.OrderWholeParam;

/**
* 订单操作
* @author xxm  
* @date 2020/11/26 
*/
public interface OrderOperateClient {

    /**
     * 传入订单和优惠, 下单
     */
    OrderDto placeOrder(OrderWholeParam orderWholeParam);

    /**
     * 付款成功状态变更
     */
    void paidOrderState(Long orderId);

    /**
     * 取消状态变更
     */
    void cancelOrderState(Long orderId);
}
