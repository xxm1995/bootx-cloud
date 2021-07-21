package cn.bootx.paymentcenter.exception.payment;

import cn.bootx.common.web.exception.BizException;
import cn.bootx.paymentcenter.code.PaymentCenterErrorCode;

/**
* 付款付款错误
* @author xxm
* @date 2020/12/8
*/
public class PaymentPayFailureException extends BizException {

    public PaymentPayFailureException() {
        super(PaymentCenterErrorCode.PAYMENT_PAY_FAILURE, "支付失败");
    }
}