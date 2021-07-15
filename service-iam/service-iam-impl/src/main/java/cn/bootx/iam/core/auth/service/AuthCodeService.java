package cn.bootx.iam.core.auth.service;

import cn.bootx.iam.core.auth.dao.AuthCodeManager;
import cn.bootx.iam.core.auth.dao.AuthCodeRepository;
import cn.bootx.iam.core.auth.entity.AuthCode;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * 授权码管理
 * @author xxm
 * @date 2020/4/27 18:07
 */
@Slf4j
@Service
@AllArgsConstructor
public class AuthCodeService {
    private final AuthCodeManager authCodeManager;
    private final AuthCodeRepository authCodeRepository;

    @Transactional(rollbackFor = Exception.class)
    public AuthCode updateAuthCode(Long authId, String client) {
        // 删除指定终端旧的authCode
        authCodeManager.removeByClientAndAuthId(authId,client);
        // 重新生成一个并保存
        AuthCode authCode = new AuthCode()
                .setAuthId(authId)
                .setClient(client)
                .setAuthCode(generateAuthCode())
                .setCreateDate(LocalDateTime.now());
        authCodeRepository.save(authCode);
        return authCode;
    }

    public AuthCode save(AuthCode code) {
        if (StrUtil.isEmpty(code.getAuthCode())) {
            code.setAuthCode(generateAuthCode())
                    .setCreateDate(LocalDateTime.now());
        }
        return authCodeRepository.save(code);
    }

    /**
     * 生成AuthCode
     */
    public String generateAuthCode() {
        return RandomUtil.randomString(10);
    }

    public Optional<AuthCode> getAuthCode(String authCode, String client) {
        return authCodeManager.findByAuthCodeAndClient(authCode, client);
    }

    public void deleteByAuthId(Long authId) {
        authCodeManager.deleteByAuthId(authId);
    }
}
