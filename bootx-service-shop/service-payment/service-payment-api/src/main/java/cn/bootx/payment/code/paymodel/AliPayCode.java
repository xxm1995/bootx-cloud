package cn.bootx.payment.code.paymodel;

/**
 * 支付宝支付参数
 * @author xxm
 * @date 2021/2/27
 */
public interface AliPayCode {

    // 响应字段
    /** 支付状态 */
    String TRADE_STATUS = "trade_status";

    /** 公用回传参数 */
    String PASS_BACK_PARAMS = "passback_params";

    /** 对交易或商品的描述(在没有公用回传参数的时候, 这个作为公用回传参数) */
    String BODY = "body";

    /** 外部订单号-paymentId */
    String OUT_TRADE_NO = "out_trade_no";

    /** 支付宝流水号 */
    String TRADE_NO = "trade_no";

    //交易状态说明
    /** 交易创建，等待买家付款 */
    String PAYMENT_WAIT_BUYER_PAY = "WAIT_BUYER_PAY";
    /** 未付款交易超时关闭，或支付完成后全额退款 */
    String PAYMENT_TRADE_CLOSED = "TRADE_CLOSED";
    /** 交易支付成功 */
    String PAYMENT_TRADE_SUCCESS = "TRADE_SUCCESS";
    /** 交易结束，不可退款 */
    String PAYMENT_TRADE_FINISHED = "TRADE_FINISHED";

    //通知触发条件
    /** 交易完成 */
    String NOTIFY_TRADE_FINISHED = "TRADE_FINISHED";
    /** 支付成功 */
    String NOTIFY_TRADE_SUCCESS = "TRADE_SUCCESS";
    /** 交易创建,不触发通知 */
    String NOTIFY_WAIT_BUYER_PAY = "WAIT_BUYER_PAY";
    /** 交易关闭 */
    String NOTIFY_TRADE_CLOSED = "TRADE_CLOSED";


    // 错误提示
    /**  */
    String ACQ_TRADE_NOT_EXIST = "ACQ.TRADE_NOT_EXIST";


    // 网关返回码
    String SUCCESS = "10000";


}
