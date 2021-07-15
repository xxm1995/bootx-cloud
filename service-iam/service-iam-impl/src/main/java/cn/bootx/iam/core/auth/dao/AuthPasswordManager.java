package cn.bootx.iam.core.auth.dao;

import cn.bootx.iam.core.auth.entity.AuthPassword;
import cn.bootx.starter.headerholder.HeaderHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
*
* @author xxm
* @date 2020/5/2 11:09
*/
@Repository
@RequiredArgsConstructor
public class AuthPasswordManager {
    private final AuthPasswordRepository userAuthRepository;

    private final HeaderHolder headerHolder;

    public Optional<AuthPassword> findByUid(Long uid) {
        return userAuthRepository.findByUidAndTid(uid,headerHolder.findTid());
    }

    public Optional<AuthPassword> findByAccount(String account) {
        return userAuthRepository.findByAccountAndTid(account,headerHolder.findTid());
    }

    public boolean existsByUid(Long uid) {
        return userAuthRepository.existsByUidAndTid(uid,headerHolder.findTid());
    }

    public boolean existsByAccount(String account) {
        return userAuthRepository.existsByAccountAndTid(account,headerHolder.findTid());
    }

    public Optional<AuthPassword> findById(Long id) {
        return userAuthRepository.findByIdAndTid(id,headerHolder.findTid());
    }

    public void update(AuthPassword userAuth) {
        userAuthRepository.save(userAuth);
    }
}
