package cn.bootx.payment.client.feign;

import cn.bootx.payment.client.PayClient;
import cn.bootx.payment.dto.payment.PaymentDto;
import cn.bootx.payment.dto.pay.PayResult;
import cn.bootx.payment.param.pay.PayParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
*
* @author xxm
* @date 2021/4/6
*/
@Component
@RequiredArgsConstructor
public class PayClientFeignImpl implements PayClient {
    @Autowired(required = false)
    private PayFeign paymentFeign;

    @Override
    public PayResult pay(PayParam payParam) {
        return paymentFeign.pay(payParam).getData();
    }

    @Override
    public void cancelByPaymentId(Long paymentId){
        paymentFeign.cancelByPaymentId(paymentId);
    }

    @Override
    public void cancelByBusinessId(String businessId){
        paymentFeign.cancelByBusinessId(businessId);
    }

    @Override
    public PaymentDto syncByBusinessId(String businessId){
        return paymentFeign.syncByBusinessId(businessId).getData();
    }
}
