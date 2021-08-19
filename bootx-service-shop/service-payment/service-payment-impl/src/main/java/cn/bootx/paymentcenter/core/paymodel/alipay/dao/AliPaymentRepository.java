package cn.bootx.paymentcenter.core.paymodel.alipay.dao;

import cn.bootx.paymentcenter.core.paymodel.alipay.entity.AliPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**   
* 支付宝支付
* @author xxm  
* @date 2021/2/26 
*/
public interface AliPaymentRepository extends JpaRepository<AliPayment,Long> {
    Optional<AliPayment> findByPaymentIdAndTid(Long paymentId, Long tid);
}
