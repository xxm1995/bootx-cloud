package cn.bootx.paymentcenter.code;

/**
* 错误码
* @author xxm  
* @date 2020/12/7 
*/
public interface PaymentCenterErrorCode {

    // 支付过程相关
    /**
     * 支付金额异常
     */
    int PAYMENT_AMOUNT_ABNORMAL = 28100;
    /**
     * 支付记录不存在
     */
    int PAYMENT_RECORD_NOT_EXISTED = 28101;
    /**
     * 支付在进行中
     */
    int PAYMENT_IS_PROCESSING = 28102;
    /**
     * 支付失败
     */
    int PAY_FAILURE = 28103;
    /**
     * 支付已经存在
     */
    int PAYMENT_HAS_EXISTED = 28104;
    /**
     * 支付手动取消
     */
    int PAYMENT_CANCEL = 28105;
    /**
     * 不支持的支付方式
     */
    int PAYMENT_METHOD_UNSUPPORT = 28106;

    // point相关

    /**
     * 积分支付记录不存在
     */
    int POINT_NOT_FOUND = 28600;

    /**
     * 积分支付记录不存在
     */
    int POINT_PAYMENT_NOT_FOUND = 28600;
    /**
     * 积分不足
     */
    int POINT_NOT_ENOUGH = 28601;
    /**
     * 积分生成错误
     */
    int POINT_GENERATE_FAILED = 28602;
    /**
     * 支付积分数量异常
     */
    int POINT_COUNT_ABNORMAL = 28603;

    /**
     * 钱包已存在
     */
    int WALLET_ALREADY_EXISTS = 28814;

    /**
     * 钱包不存在
     */
    int WALLET_NOT_EXISTS = 28815;

    /**
     * 钱包已被禁用
     */
    int WALLET_BANNED = 28816;

    /**
     * 钱包余额不足
     */
    int WALLET_BALANCE_NOT_ENOUGH = 28817;

    /**
     * Token过期
     */
    int WALLET_TOKEN_EXPIRED = 28818;

    /**
     * wallet 信息不存在
     */
    int WALLET_INFO_NOT_EXISTS = 28819;

    /**
     * 钱包Token与租户不匹配
     */
    int WALLET_TOKEN_MATCH_TENANT_ERROR = 28820;

    /**
     * 积分返还异常
     */
    int POINT_RESTORE_ERROR = 28823;

    /**
     * 钱包日志异常(类型不正确，或者充值金额小于0等场景)
     */
    int WALLET_LOG_ERROR = 28827;

}
