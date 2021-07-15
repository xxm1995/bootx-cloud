package cn.bootx.iam.core.auth.dao;

import cn.bootx.iam.core.auth.entity.AuthCode;
import cn.bootx.starter.headerholder.HeaderHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
* @author xxm
* @date 2020/4/27 18:07
*/
@Repository
@RequiredArgsConstructor
public class AuthCodeManager{
    private final AuthCodeRepository authCodeRepository;

    private final HeaderHolder headerHolder;

    public void removeByClientAndAuthId(Long authId,String client) {
        this.authCodeRepository.deleteByAuthIdAndClientAndTid(authId,client,headerHolder.findTid());
    }

    public Optional<AuthCode> findByAuthCodeAndClient(String authCode, String client) {
        return authCodeRepository.findByAuthCodeAndClientAndTid(authCode,client,headerHolder.findTid());
    }

    public void deleteByAuthId(Long authId) {
        authCodeRepository.deleteByAuthIdAndTid(authId,headerHolder.findTid());
    }
}
