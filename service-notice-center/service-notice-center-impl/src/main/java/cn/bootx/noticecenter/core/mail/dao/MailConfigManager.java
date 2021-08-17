package cn.bootx.noticecenter.core.mail.dao;

import cn.bootx.noticecenter.core.mail.entity.MailConfig;
import cn.bootx.common.headerholder.HeaderHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
* @author xxm
* @date 2020/4/8 13:27
*/
@Slf4j
@Repository
@RequiredArgsConstructor
public class MailConfigManager {

    private final MailConfigRepository mailConfigRepository;

    private final HeaderHolder headerHolder;

    public Optional<MailConfig> findByDefault() {
        return mailConfigRepository.findByIsDefaultAndTid(true,headerHolder.findTid());
    }

    public List<MailConfig> findAll() {
        return mailConfigRepository.findAllByTid(headerHolder.findTid());
    }

    public Optional<MailConfig> findById(Long id) {
        return  mailConfigRepository.findByIdAndTid(id,headerHolder.findTid());
    }

    public Optional<MailConfig> findByCode(String code) {
        return  mailConfigRepository.findByCodeAndTid(code,headerHolder.findTid());
    }

    public boolean existsByCode(String code) {
        return mailConfigRepository.existsByCodeAndTid(code,headerHolder.findTid());
    }

    public boolean existsByDefault() {
        return mailConfigRepository.existsByIsDefaultAndTid(true,headerHolder.findTid());
    }

    public void deleteById(Long id) {
        mailConfigRepository.deleteByIdAndTid(id,headerHolder.findTid());
    }
}
