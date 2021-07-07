package cn.bootx.paymentcenter.core.paymodel.cash.dao;

import cn.bootx.paymentcenter.core.paymodel.cash.entity.CashPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**   
* 现金支付
* @author xxm  
* @date 2021/6/23 
*/
public interface CashPaymentRepository extends JpaRepository<CashPayment, Long> {
    Optional<CashPayment> findByPaymentIdAndTid(Long paymentId, Long tid);
}
