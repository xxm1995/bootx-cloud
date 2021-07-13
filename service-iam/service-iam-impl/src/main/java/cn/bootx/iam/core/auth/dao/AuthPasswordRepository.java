package cn.bootx.iam.core.auth.dao;

import cn.bootx.iam.core.auth.entity.AuthPassword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthPasswordRepository extends JpaRepository<AuthPassword,Long> {

    Optional<AuthPassword> findByUidAndTid(Long id, Long tid);

    Optional<AuthPassword> findByAccountAndTid(String account, Long tid);

    boolean existsByUidAndTid(Long uid, Long tid);

    boolean existsByAccountAndTid(String account, Long tid);

    Optional<AuthPassword> findByIdAndTid(Long id, Long tid);
}
