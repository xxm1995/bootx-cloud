package cn.bootx.noticecenter.core.mail.dao;

import cn.bootx.noticecenter.core.mail.entity.MailTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MailTemplateRepository extends JpaRepository<MailTemplate,Long> {

    boolean existsByCodeAndTid(String code, Long tid);

    boolean existsByCodeAndIdNotAndTid(String code, Long id, Long tid);

    Optional<MailTemplate> findByIdAndTid(Long id, Long tid);

    void deleteByIdAndTid(Long id, Long tid);
}
