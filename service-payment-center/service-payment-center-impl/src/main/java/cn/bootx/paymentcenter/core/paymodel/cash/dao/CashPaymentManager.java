package cn.bootx.paymentcenter.core.paymodel.cash.dao;

import cn.bootx.paymentcenter.core.paymodel.cash.entity.CashPayment;
import cn.bootx.starter.headerholder.HeaderHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**   
* 现金支付
* @author xxm  
* @date 2021/6/23 
*/
@Slf4j
@Repository
@RequiredArgsConstructor
public class CashPaymentManager {
    private final CashPaymentRepository repository;
    private final HeaderHolder headerHolder;

    public Optional<CashPayment> findByPaymentId(Long paymentId) {
        return repository.findByPaymentIdAndTid(paymentId,headerHolder.findTid());
    }
}
