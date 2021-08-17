package cn.bootx.paymentcenter.core.paymodel.alipay.dao;

import cn.bootx.paymentcenter.core.paymodel.alipay.entity.AliPayment;
import cn.bootx.common.headerholder.HeaderHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**   
* 支付宝
* @author xxm  
* @date 2021/2/26 
*/
@Repository
@RequiredArgsConstructor
public class AliPaymentManager {
    private final AliPaymentRepository aliPaymentRepository;

    private final HeaderHolder headerHolder;

    public Optional<AliPayment> findByPaymentId(Long paymentId) {
        return aliPaymentRepository.findByPaymentIdAndTid(paymentId,headerHolder.findTid());
    }
}
