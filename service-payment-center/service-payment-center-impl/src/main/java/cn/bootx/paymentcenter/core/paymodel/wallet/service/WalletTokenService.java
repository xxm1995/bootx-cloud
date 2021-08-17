package cn.bootx.paymentcenter.core.paymodel.wallet.service;

import cn.bootx.paymentcenter.core.paymodel.wallet.dao.WalletManager;
import cn.bootx.common.headerholder.HeaderHolder;
import cn.bootx.common.redis.RedisClient;
import cn.bootx.common.snowflake.SnowFlakeId;
import com.alibaba.csp.sentinel.util.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class WalletTokenService {

    public static final long TIMEOUT_5_M = 5 * 60 * 1000L;

    public static final String WALLET_PREFIX = "wallet:wallet_";

    public static final String WALLET_TOKEN_PREFIX = "wallet:token_";

    public static final String TOKEN_PREFIX = "ez-";

    private final WalletManager walletManager;

    private final RedisClient redisClient;
    private final HeaderHolder headerHolder;
    private final SnowFlakeId snowFlakeId;

    /**
     * 生成并保存钱包Token
     */
    public String generateAndGetWalletToken(Long walletId) {

        // 删除已存在的Token
        deleteExistsToken(walletId);

        String tid = "-" + headerHolder.findTid();
        String tokenOriginal = TOKEN_PREFIX + snowFlakeId.nextIdStr();

        String token = tokenOriginal + tid;

        // 生成Token并保存
        redisClient.setWithTimeout(this.getTokenByWalletKey(walletId), token, TIMEOUT_5_M);
        redisClient.setWithTimeout(this.getWalletByTokenKey(token), String.valueOf(walletId), TIMEOUT_5_M);

        return tokenOriginal;
    }

    /**
     * 删除token
     */
    private void deleteExistsToken(Long walletId) {
        // 获取已有的Token,存在则删除
        String token = redisClient.get(getTokenByWalletKey(walletId));
        if (StringUtil.isNotBlank(token)) {
            redisClient.deleteKey(getWalletByTokenKey(token));
        }
    }

    /**
     * 根据token获取钱包Id
     */
    public String getWalletByToken(String token) {
        String tid = "-" + headerHolder.findTid();
        return redisClient.get(getWalletByTokenKey(token+tid));
    }

    /**
     * 根据钱包token删除id
     */
    public void deleteWalletId(String walletToken) {
        String tid = "-" + headerHolder.findTid();
        redisClient.deleteKey(getWalletByTokenKey(walletToken+tid));
    }

    /**
     * 根据钱包id删除token
     */
    public void deleteWalletToken(Long walletId) {
        redisClient.deleteKey(getTokenByWalletKey(walletId));
    }

    /**
     * 生成Token
     */
    private String getTokenByWalletKey(Long walletId) {
        return WALLET_PREFIX + walletId;
    }

    /**
     * 生成WalletId
     */
    private String getWalletByTokenKey(String token) {
        return WALLET_TOKEN_PREFIX + token;
    }

    /**
     * 验证token租户
     */
    public boolean validationTenant(String walletToken){
        // 解析Token 判断Token与请求租户是否一致
        String[] split = walletToken.split("-");
        return split.length == 3 && Objects.equals(split[2], String.valueOf(headerHolder.findTid()));
    }

}
