package cn.bootx.noticecenter.core.mail.dao;

import cn.bootx.noticecenter.core.mail.entity.MailConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
* @author xxm
* @date 2020/4/8 13:22
*/
public interface MailConfigRepository extends JpaRepository<MailConfig,Long> {
    Optional<MailConfig> findByIsDefaultAndTid(boolean isDefault, Long tid);

    List<MailConfig> findAllByTid(Long tid);

    Optional<MailConfig> findByIdAndTid(Long id, Long tid);

    Optional<MailConfig> findByCodeAndTid(String code, Long tid);

    boolean existsByCodeAndTid(String code, Long tid);

    boolean existsByIsDefaultAndTid(boolean isDefault, Long tid);

    void deleteByIdAndTid(Long id,Long tid);
}
