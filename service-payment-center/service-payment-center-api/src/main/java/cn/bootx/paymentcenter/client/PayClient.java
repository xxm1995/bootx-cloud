package cn.bootx.paymentcenter.client;

import cn.bootx.paymentcenter.dto.payment.PaymentDto;
import cn.bootx.paymentcenter.dto.pay.PayResult;
import cn.bootx.paymentcenter.param.pay.PayParam;

/**
* 支付接口
* @author xxm  
* @date 2020/12/10 
*/
public interface PayClient {

    /**
     * 支付
     */
    PayResult pay(PayParam payParam);

    /**
     * 取消支付
     */
    void cancelByPaymentId(Long paymentId);

    /**
     * 取消支付
     */
    void cancelByBusinessId(String businessId);

    /**
     * 同步支付状态
     */
    PaymentDto syncByBusinessId(String businessId);
}
