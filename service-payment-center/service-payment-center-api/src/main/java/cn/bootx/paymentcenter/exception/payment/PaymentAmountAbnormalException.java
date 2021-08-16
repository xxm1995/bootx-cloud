package cn.bootx.paymentcenter.exception.payment;

import cn.bootx.common.core.exception.FatalException;
import cn.bootx.paymentcenter.code.PaymentCenterErrorCode;

/**   
* 异常金额
* @author xxm  
* @date 2020/12/8 
*/
public class PaymentAmountAbnormalException extends FatalException {

    public PaymentAmountAbnormalException() {
        super(PaymentCenterErrorCode.PAYMENT_AMOUNT_ABNORMAL, "异常金额");
    }
}