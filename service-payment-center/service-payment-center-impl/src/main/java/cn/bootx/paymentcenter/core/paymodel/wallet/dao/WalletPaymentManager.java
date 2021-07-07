package cn.bootx.paymentcenter.core.paymodel.wallet.dao;

import cn.bootx.paymentcenter.core.paymodel.wallet.entity.WalletPayment;
import cn.bootx.starter.headerholder.HeaderHolder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class WalletPaymentManager {
    private final WalletPaymentRepository walletPaymentRepository;
    private final JPAQueryFactory jpaQueryFactory;

    private final HeaderHolder headerHolder;

    public Optional<WalletPayment> findByPaymentId(Long paymentId) {
        return walletPaymentRepository.findByPaymentIdAndTid(paymentId,headerHolder.findTid());
    }

    public Optional<WalletPayment> findById(Long id) {
        return walletPaymentRepository.findByIdAndTid(id,headerHolder.findTid());
    }
}
