package cn.bootx.paymentcenter.core.paymodel.wallet.dao;

import cn.bootx.paymentcenter.core.paymodel.wallet.entity.Wallet;
import cn.bootx.common.headerholder.HeaderHolder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

/**   
* 钱包管理
* @author xxm  
* @date 2020/12/8 
*/
@Repository
@RequiredArgsConstructor
public class WalletManager {
    private final WalletRepository walletRepository;
    private final JPAQueryFactory jpaQueryFactory;

    private final HeaderHolder headerHolder;

    /**
     * 更新余额
     *
     * @param walletId 钱包
     * @param amount   金额
     * @param operator 操作人
     * @return 更新数量
     */
    public int increaseBalance(Long walletId, BigDecimal amount, Long operator) {
        return walletRepository.increaseBalance(walletId, amount, operator, LocalDateTime.now(),headerHolder.findTid());
    }


    /**
     * 根据钱包ID查询
     */
    public Optional<Wallet> findById(Long id) {
        return walletRepository.findByIdAndTid(id,headerHolder.findTid());
    }

    /**
     * 扣减余额
     *
     * @param walletId 钱包ID
     * @param amount   扣减金额
     * @return 剩余条数
     */
    public int reduceBalance(Long walletId, BigDecimal amount) {
        return walletRepository.reduceBalance(walletId, amount, headerHolder.getOperatorId(), LocalDateTime.now(),headerHolder.findTid());
    }

    /**
     * 扣减余额-允许扣成负数
     *
     * @param walletId 钱包ID
     * @param amount   扣减金额
     * @param operator 操作人
     * @return 剩余条数
     */
    public int reduceBalanceUnlimited(Long walletId, BigDecimal amount, Long operator) {
        return walletRepository.reduceBalanceUnlimited(walletId, amount, operator, LocalDateTime.now(),headerHolder.findTid());
    }

    /**
     * 用户钱包是否存在
     */
    public boolean existsByUser(Long userId) {
        return walletRepository.existsByUserIdAndTid(userId,headerHolder.findTid());
    }

    /**
     * 查询用户的钱包
     */
    public Optional<Wallet> findByUser(Long userId) {
        return walletRepository.findByUserIdAndTid(userId,headerHolder.findTid());
    }
}
