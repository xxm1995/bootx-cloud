package cn.bootx.iam.core.user.dao;

import cn.bootx.iam.core.user.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {
    Optional<UserInfo> findByIdAndTid(Long id, Long tid);

    List<UserInfo> findByIdInAndTid(List<Long> ids, Long tid);

    boolean existsByIdAndTid(Long id, Long tid);

    boolean existsByAccountAndTid(String account, Long tid);

    boolean existsByEmailAndTid(String email,Long tid);

    boolean existsByPhoneAndTid(String phone,Long tid);

    Optional<UserInfo> findByAccountAndTid(String account, Long tid);

    Optional<UserInfo> findByEmailAndTid(String email, Long tid);

    Optional<UserInfo> findByPhoneAndTid(String phone, Long tid);


}
