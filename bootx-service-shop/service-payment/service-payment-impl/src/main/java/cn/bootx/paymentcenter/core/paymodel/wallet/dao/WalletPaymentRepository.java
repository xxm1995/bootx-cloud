package cn.bootx.paymentcenter.core.paymodel.wallet.dao;

import cn.bootx.paymentcenter.core.paymodel.wallet.entity.WalletPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletPaymentRepository extends JpaRepository<WalletPayment,Long> {

    Optional<WalletPayment> findByPaymentIdAndTid(Long paymentId, Long tid);

    Optional<WalletPayment> findByIdAndTid(Long id, Long tid);
}
