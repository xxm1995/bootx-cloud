package cn.bootx.order.client;


/**
* 订单操作
* @author xxm  
* @date 2020/11/26 
*/
public interface OrderOperateClient {

    /**
     * 付款成功状态变更
     */
    void paidOrderState(Long orderId);

    /**
     * 取消状态变更
     */
    void cancelOrderState(Long orderId);
}
