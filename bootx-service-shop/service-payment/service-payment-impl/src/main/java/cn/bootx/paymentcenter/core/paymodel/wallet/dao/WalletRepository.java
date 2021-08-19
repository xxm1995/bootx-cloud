package cn.bootx.paymentcenter.core.paymodel.wallet.dao;

import cn.bootx.paymentcenter.core.paymodel.wallet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * 钱包
 * @author xxm
 * @date 2021/2/24
 */
public interface WalletRepository extends JpaRepository<Wallet, Long> {


    /**
     * 增加余额
     *
     * @param walletId 钱包ID
     * @param amount   增加的额度
     * @param operator 操作人
     * @param date     时间
     * @return 更新数量
     */
    @Modifying(flushAutomatically = true)
    @Query("update Wallet set balance = (balance + ?2),lastModifier = ?3,lastModifiedTime = ?4,version = (version+1) where id = ?1 and tid = ?5")
    int increaseBalance(Long walletId, BigDecimal amount, Long operator, LocalDateTime date, Long tid);

    /**
     * 减少余额
     *
     * @param walletId 钱包ID
     * @param amount   减少的额度
     * @param operator 操作人
     * @param date     操作时间
     * @return 操作条数
     */
    @Modifying(flushAutomatically = true)
    @Query("update Wallet set balance = (balance - ?2),lastModifier = ?3,lastModifiedTime = ?4,version = (version+1) where id = ?1 and tid = ?5 and (balance-?2) >= 0")
    int reduceBalance(Long walletId, BigDecimal amount, Long operator, LocalDateTime date, Long tid);

    /**
     * 减少余额,允许扣成负数
     *
     * @param walletId 钱包ID
     * @param amount   减少的额度
     * @param operator 操作人
     * @param date     操作时间
     * @return 操作条数
     */
    @Modifying(flushAutomatically = true)
    @Query("update Wallet set balance = (balance - ?2),lastModifier = ?3,lastModifiedTime = ?4,version = (version+1) where id = ?1 and tid = ?5")
    int reduceBalanceUnlimited(Long walletId, BigDecimal amount, Long operator, LocalDateTime date, Long tid);

    boolean existsByUserIdAndTid(Long userId,Long tid);

    Optional<Wallet> findByUserIdAndTid(Long userId, Long tid);

    Optional<Wallet> findByIdAndTid(Long id, Long tid);
}
