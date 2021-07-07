package cn.bootx.paymentcenter.core.paymodel.wallet.service;

import cn.bootx.common.util.BigDecimalUtil;
import cn.bootx.common.web.exception.BizException;
import cn.bootx.paymentcenter.code.paymodel.WalletCode;
import cn.bootx.paymentcenter.code.paymodel.WalletProtectionMode;
import cn.bootx.paymentcenter.core.paymodel.wallet.dao.WalletLogRepository;
import cn.bootx.paymentcenter.core.paymodel.wallet.dao.WalletManager;
import cn.bootx.paymentcenter.core.paymodel.wallet.dao.WalletRepository;
import cn.bootx.paymentcenter.core.paymodel.wallet.entity.Wallet;
import cn.bootx.paymentcenter.core.paymodel.wallet.entity.WalletLog;
import cn.bootx.paymentcenter.dto.paymodel.wallet.WalletDto;
import cn.bootx.paymentcenter.exception.waller.WalletBannedException;
import cn.bootx.paymentcenter.exception.waller.WalletNotExistsException;
import cn.bootx.paymentcenter.exception.waller.WalletTokenExpiredException;
import cn.bootx.paymentcenter.param.paymodel.wallet.WalletActiveParam;
import cn.bootx.paymentcenter.param.paymodel.wallet.WalletProtectionSettingParam;
import cn.bootx.paymentcenter.param.paymodel.wallet.WalletRechargeParam;
import com.alibaba.csp.sentinel.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

/**
 * 钱包的相关操作
 * @author xxm
 * @date 2020/12/8
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WalletService{
    private final WalletTokenService walletTokenService;

    private final WalletManager walletManager;

    private final WalletRepository walletRepository;
    private final WalletLogRepository walletLogRepository;

    /**
     * 开通操作
     */
    @Transactional(rollbackFor = Exception.class)
    public WalletDto activeWallet(WalletActiveParam param){
        // 判断钱包是否已开通
        if (walletManager.existsByUser(param.getUserId())) {
            throw new BizException("钱包已经开通");
        }
        Wallet wallet = new Wallet()
                .setUserId(param.getUserId())
                .setProtectionMode(WalletCode.PROTECTION_MODE_NONE)
                .setStatus(WalletCode.WALLET_STATUS_NORMAL)
                .setBalance(Optional.ofNullable(param.getPresentBalance()).orElse(BigDecimal.ZERO));
        Wallet save = walletRepository.save(wallet);

        // 激活 log
        WalletLog activeLog = new WalletLog()
                .setWalletId(wallet.getId())
                .setUserId(wallet.getUserId())
                .setType(WalletCode.WALLET_LOG_ACTIVE)
                .setRemark("激活钱包")
                .setOperationSource(WalletCode.OPERATION_SOURCE_USER);
        walletLogRepository.save(activeLog);

        // 赠送记录log
        if (BigDecimalUtil.compareTo(wallet.getBalance(), BigDecimal.ZERO) > 0) {
            WalletLog presentLog = new WalletLog()
                    .setWalletId(wallet.getId())
                    .setUserId(wallet.getUserId())
                    .setAmount(wallet.getBalance())
                    .setType(WalletCode.WALLET_LOG_PRESENT)
                    .setRemark("赠送")
                    .setOperationSource(WalletCode.OPERATION_SOURCE_SYSTEM);
            walletLogRepository.save(presentLog);
        }
        return save.toDto();

    }

    /**
     * 安全设置
     */
    @Transactional(rollbackFor = Exception.class)
    public WalletDto setProtection(WalletProtectionSettingParam param){
        Wallet wallet = this.getNormalWalletById(param.getWalletId());
        wallet.setPinCode(param.getPinCode());
        wallet.setProtectionMode(param.getProtectionMode());
        Wallet save = walletRepository.save(wallet);

        // 日志
        WalletLog walletLog = new WalletLog()
                .setWalletId(wallet.getId())
                .setUserId(wallet.getUserId())
                .setType(WalletCode.WALLET_CHANGE_PROTECTION_MODE)
                .setRemark(String.format("钱包更改保护模式为%s", WalletProtectionMode.getName(wallet.getProtectionMode())))
                .setOperationSource(WalletCode.OPERATION_SOURCE_USER);
       walletLogRepository.save(walletLog);
       return save.toDto();
    }

    /**
     * 充值操作
     */
    @Transactional(rollbackFor = Exception.class)
    public void recharge(WalletRechargeParam param){
        Wallet wallet = this.getNormalWalletById(param.getWalletId());
        walletManager.increaseBalance(param.getWalletId(), param.getAmount(), param.getOperatorId());

        WalletLog walletLog = new WalletLog()
                .setAmount(param.getAmount())
                .setPaymentId(param.getPaymentId())
                .setWalletId(wallet.getId())
                .setUserId(wallet.getUserId())
                .setType(param.getType())
                .setRemark(String.format("钱包充值金额 %.2f ", param.getAmount()))
                .setOperationSource(param.getOperationSource())
                .setBusinessId(param.getBusinessId());
        walletLogRepository.save(walletLog);
    }

    /**
     * 根据ID查询Wallet
     */
    public WalletDto getById(Long walletId) {
        return walletManager.findById(walletId).map(Wallet::toDto).orElse(null);
    }

    /**
     * 根据用户ID查询钱包
     */
    public WalletDto getByUserId(Long userId) {
        return walletManager.findByUser(userId).map(Wallet::toDto).orElse(null);
    }

    /**
     * 生成指定用户的钱包Token
     */
    public String generateWalletToken(Long userId) {
        Wallet wallet = this.getNormalWalletByUserId(userId);
        return walletTokenService.generateAndGetWalletToken(wallet.getId());
    }

    /**
     * 根据token查询钱包
     */
    public WalletDto getByToken(String token, boolean deleteToken) {
        Long walletId = Optional.ofNullable(walletTokenService.getWalletByToken(token))
                .map(Long::valueOf)
                .orElseThrow(WalletTokenExpiredException::new);
        Wallet wallet = this.getNormalWalletById(walletId);

        // 是否删除Token
        if (deleteToken) {
            walletTokenService.deleteWalletId(token);
            walletTokenService.deleteWalletToken(walletId);
        }
        return wallet.toDto();
    }

    /**
     * 获取Token的钱包ID
     */
    public Long getAndDeleteWalletId(String walletToken) {
        if (StringUtil.isBlank(walletToken)) {
            return null;
        }
        String walletIdStr = walletTokenService.getWalletByToken(walletToken);
        walletTokenService.deleteWalletId(walletToken);
        if (walletIdStr != null) {
            Long walletId = Long.valueOf(walletIdStr);
            walletTokenService.deleteWalletToken(walletId);
            return walletId;
        }
        return null;
    }

    /**
     * 查询钱包，如果钱包不存在或者钱包被禁用将抛出异常
     */
    public Wallet getNormalWalletById(Long walletId) {
        // 查询Wallet
        Wallet wallet = walletManager.findById(walletId).orElseThrow(WalletNotExistsException::new);

        // 是否被禁用
        if (Objects.equals(WalletCode.WALLET_STATUS_FORBIDDEN, wallet.getStatus())) {
            throw new WalletBannedException();
        }
        return wallet;
    }
    /**
     * 查询钱包，如果钱包不存在或者钱包被禁用将抛出异常
     */
    private Wallet getNormalWalletByUserId(Long userId) {
        // 查询Wallet
        Wallet wallet = walletManager.findByUser(userId).orElseThrow(WalletNotExistsException::new);

        // 是否被禁用
        if (Objects.equals(WalletCode.WALLET_STATUS_FORBIDDEN, wallet.getStatus())) {
            throw new WalletBannedException();
        }
        return wallet;
    }
}
