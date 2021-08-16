package cn.bootx.noticecenter.core.mail.dao;

import cn.bootx.common.core.rest.param.PageParam;
import cn.bootx.noticecenter.core.mail.entity.MailTemplate;
import cn.bootx.noticecenter.core.mail.entity.QMailTemplate;
import cn.bootx.starter.headerholder.HeaderHolder;
import cn.bootx.starter.jpa.utils.JpaUtils;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
*
* @author xxm
* @date 2020/11/18
*/
@Repository
@AllArgsConstructor
public class MailTemplateManager {
    private final MailTemplateRepository mailTemplateRepository;
    private final JPAQueryFactory jpaQueryFactory;

    private final HeaderHolder headerHolder;

    public boolean existsByCode(String code) {
        return mailTemplateRepository.existsByCodeAndTid(code,headerHolder.findTid());
    }

    public boolean existsByCode(String code,Long id) {
        return mailTemplateRepository.existsByCodeAndIdNotAndTid(code,id,headerHolder.findTid());
    }

    public Optional<MailTemplate> findById(Long id) {
        return mailTemplateRepository.findByIdAndTid(id,headerHolder.findTid());
    }

    public Page<MailTemplate> page(PageParam param) {
        QMailTemplate q = QMailTemplate.mailTemplate;
        JPAQuery<MailTemplate> query = jpaQueryFactory.selectFrom(q);
        return JpaUtils.queryPage(query, param);
    }

    public void deleteById(Long id) {
        mailTemplateRepository.deleteByIdAndTid(id,headerHolder.findTid());
    }

}
