package cn.bootx.paymentcenter.core.payment.service;

import cn.bootx.paymentcenter.code.pay.PayStatusCode;
import cn.bootx.paymentcenter.core.payment.dao.PaymentManager;
import cn.bootx.paymentcenter.core.payment.entity.Payment;
import cn.bootx.paymentcenter.exception.payment.PaymentIsProcessingException;
import cn.hutool.core.collection.CollectionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**   
* 支付交易单
* @author xxm  
* @date 2021/3/8 
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentManager paymentManager;

    /**
     * 校验支付状态，支付成功则返回，支付失败/支付进行中则抛出对应的异常
     */
    public Payment getByBusinessId(String businessId) {

        Payment payment;
        // 根据订单查询支付记录
        List<Payment> payments = paymentManager.findByBusinessIdNoCancelDesc(businessId);
        if (!CollectionUtil.isEmpty(payments)) {
            payment = payments.get(0);

            // 成功 或 异步支付
            if (payment.getPayStatus() == PayStatusCode.TRADE_SUCCESS || payment.isSyncPayMode()) {
                return payment;
            }

            // 支付中 (非异步支付方式下)
            if (payment.getPayStatus() == PayStatusCode.TRADE_PROGRESS) {
                throw new PaymentIsProcessingException();
            }
        }
        return null;
    }

    /**
     * 查找超时付款订单
     */
    public List<Long> findPayTimeoutIds() {
        LocalDateTime dateTime = LocalDateTime.now().minusMinutes(30);
        return paymentManager.findPayTimeoutIds(dateTime);
    }


}
