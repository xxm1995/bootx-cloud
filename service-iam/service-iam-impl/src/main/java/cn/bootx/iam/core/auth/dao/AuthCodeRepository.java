package cn.bootx.iam.core.auth.dao;

import cn.bootx.iam.core.auth.entity.AuthCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
* @author xxm
* @date 2020/4/27 18:06
*/
public interface AuthCodeRepository extends JpaRepository<AuthCode,Long> {

    void deleteByAuthIdAndTid(Long authId, Long tid);

    Optional<AuthCode> findByAuthCodeAndClientAndTid(String authCode, String client, Long tid);

    void deleteByAuthIdAndClientAndTid(Long authId, String client, Long tid);
}
