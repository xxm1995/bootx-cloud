package cn.bootx.payment.event;

/**
 * 支付中心消息常量
 * @author xxm
 * @date 2021/4/19
 */
public interface PaymentEventCode {

    /** 支付交换机 */
    String EXCHANGE_PAYMENT = "service.payment.center";

    /** 支付完成 */
    String PAY_COMPLETE = "pay.complete";

    /** 支付撤销 */
    String PAY_CANCEL = "pay.cancel";

}
