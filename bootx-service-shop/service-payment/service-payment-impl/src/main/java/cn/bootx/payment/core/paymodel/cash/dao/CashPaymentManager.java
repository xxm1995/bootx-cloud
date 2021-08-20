package cn.bootx.payment.core.paymodel.cash.dao;

import cn.bootx.common.mybatisplus.impl.BaseManager;
import cn.bootx.payment.core.paymodel.cash.entity.CashPayment;
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
public class CashPaymentManager extends BaseManager<CashPaymentMapper,CashPayment> {

    public Optional<CashPayment> findByPaymentId(Long paymentId) {
        return findByField(CashPayment::getPaymentId,paymentId);
    }
}
