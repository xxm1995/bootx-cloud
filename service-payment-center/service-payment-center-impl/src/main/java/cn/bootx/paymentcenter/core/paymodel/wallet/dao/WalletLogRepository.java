package cn.bootx.paymentcenter.core.paymodel.wallet.dao;

import cn.bootx.paymentcenter.core.paymodel.wallet.entity.WalletLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 钱包日志
 * @author xxm
 * @date 2020/12/8
 */
public interface WalletLogRepository extends JpaRepository<WalletLog,Long> {
}
