package cn.bootx.ordercenter.event;

/**
 * 订单中心事件值
 * @author xxm
 * @date 2021/4/22
 */
public interface OrderEventCode {

    /** 交换机 */
    String EXCHANGE_ORDER = "service.order.center";

    /** 订单取消事件 发 */
    String ORDER_CANCEL = "order.cancel";

    /** 订单完成事件 */
    String ORDER_COMPLETE = "order.complete";

}
