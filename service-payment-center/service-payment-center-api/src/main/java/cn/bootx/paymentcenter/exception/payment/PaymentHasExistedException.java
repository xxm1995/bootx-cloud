package cn.bootx.paymentcenter.exception.payment;

import cn.bootx.common.core.exception.BizException;
import cn.bootx.paymentcenter.code.PaymentCenterErrorCode;

/**   
* 付款已存在
* @author xxm  
* @date 2020/12/8 
*/
public class PaymentHasExistedException extends BizException {

    public PaymentHasExistedException() {
        super(PaymentCenterErrorCode.PAYMENT_HAS_EXISTED, "付款已存在");
    }
}