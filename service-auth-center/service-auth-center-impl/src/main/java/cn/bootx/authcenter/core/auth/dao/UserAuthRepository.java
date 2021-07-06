package cn.bootx.authcenter.core.auth.dao;

import cn.bootx.authcenter.core.auth.entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAuthRepository extends JpaRepository<UserAuth,Long> {

    Optional<UserAuth> findByUidAndTid(Long id, Long tid);

    Optional<UserAuth> findByAccountAndTid(String account, Long tid);

    boolean existsByUidAndTid(Long uid, Long tid);

    boolean existsByAccountAndTid(String account, Long tid);

    Optional<UserAuth> findByIdAndTid(Long id, Long tid);
}
