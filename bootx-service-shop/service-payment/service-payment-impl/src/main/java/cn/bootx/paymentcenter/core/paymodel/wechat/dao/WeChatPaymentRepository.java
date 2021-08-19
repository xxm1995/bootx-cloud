package cn.bootx.paymentcenter.core.paymodel.wechat.dao;

import cn.bootx.paymentcenter.core.paymodel.wechat.entity.WeChatPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeChatPaymentRepository extends JpaRepository<WeChatPayment,Long> {

    Optional<WeChatPayment> findByPaymentIdAndTid(Long paymentId,Long tid);
}
