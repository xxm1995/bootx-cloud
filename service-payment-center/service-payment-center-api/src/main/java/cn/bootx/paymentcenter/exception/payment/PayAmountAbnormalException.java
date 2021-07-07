package cn.bootx.paymentcenter.exception.payment;

import cn.bootx.common.web.exception.FatalException;
import cn.bootx.paymentcenter.code.PaymentCenterErrorCode;

/**   
* 异常金额
* @author xxm  
* @date 2020/12/8 
*/
public class PayAmountAbnormalException extends FatalException {

    public PayAmountAbnormalException() {
        super(PaymentCenterErrorCode.PAYMENT_AMOUNT_ABNORMAL, "异常金额");
    }
}