package cn.bootx.starter.jpa;

import cn.bootx.starter.auth.utils.SecurityUtils;
import cn.bootx.starter.headerholder.HeaderHolder;
import cn.bootx.starter.jpa.audit.JpaAuditorAware;
import cn.bootx.starter.jpa.audit.JpaEntityAuditListener;
import cn.bootx.starter.snowflake.SnowFlakeId;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityManager;

@EnableJpaAuditing
@Configuration
@ConditionalOnClass(SnowflakeIdStrategy.class)
public class JpaConfiguration {

    @Bean
    public SnowflakeIdStrategy snowflakeIdStrategy(SnowFlakeId snowFlakeId){
        return new SnowflakeIdStrategy(snowFlakeId);
    }

    @Bean
    public JpaEntityAuditListener jpaEntityAuditListener(HeaderHolder headerHolder){
        return new JpaEntityAuditListener(headerHolder);
    }

    @Bean
    public JpaAuditorAware jpaAuditorAware(SecurityUtils securityUtils){
        return new JpaAuditorAware(securityUtils);
    }

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager entityManager){
        return new JPAQueryFactory(entityManager);
    }


}
