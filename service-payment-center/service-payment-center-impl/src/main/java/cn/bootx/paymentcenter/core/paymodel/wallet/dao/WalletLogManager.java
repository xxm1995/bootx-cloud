package cn.bootx.paymentcenter.core.paymodel.wallet.dao;

import cn.bootx.paymentcenter.core.paymodel.wallet.entity.WalletLog;
import cn.bootx.starter.headerholder.HeaderHolder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
* 钱包日志
* @author xxm
* @date 2020/12/8
*/
@Repository
@RequiredArgsConstructor
public class WalletLogManager {
    private final WalletLogRepository walletLogRepository;
    private final JPAQueryFactory jpaQueryFactory;

    private final HeaderHolder headerHolder;

    public Optional<WalletLog> findFirstByPayment(Long paymentId) {
        return null;
    }
}
