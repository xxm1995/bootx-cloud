package cn.bootx.authcenter.core.auth.dao;

import cn.bootx.authcenter.core.auth.entity.UserAuth;
import cn.bootx.starter.headerholder.HeaderHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
*
* @author xxm
* @date 2020/5/2 11:09
*/
@Repository
@RequiredArgsConstructor
public class UserAuthManager{
    private final UserAuthRepository userAuthRepository;

    private final HeaderHolder headerHolder;

    public Optional<UserAuth> findByUid(Long uid) {
        return userAuthRepository.findByUidAndTid(uid,headerHolder.findTid());
    }

    public Optional<UserAuth> findByAccount(String account) {
        return userAuthRepository.findByAccountAndTid(account,headerHolder.findTid());
    }

    public boolean existsByUid(Long uid) {
        return userAuthRepository.existsByUidAndTid(uid,headerHolder.findTid());
    }

    public boolean existsByAccount(String account) {
        return userAuthRepository.existsByAccountAndTid(account,headerHolder.findTid());
    }

    public Optional<UserAuth> findById(Long id) {
        return userAuthRepository.findByIdAndTid(id,headerHolder.findTid());
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(UserAuth userAuth) {
        userAuthRepository.save(userAuth);
    }
}
