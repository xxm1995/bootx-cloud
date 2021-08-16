package cn.bootx.starter.jpa.base;


import cn.bootx.starter.jpa.JpaConst;
import cn.bootx.starter.jpa.audit.JpaEntityAuditListener;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@MappedSuperclass
@EntityListeners({JpaEntityAuditListener.class})
public class JpaTidEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = JpaConst.SNOWFLAKE_ID_STRATEGY)
    @GenericGenerator(name = JpaConst.SNOWFLAKE_ID_STRATEGY, strategy = JpaConst.SNOWFLAKE_ID_STRATEGY_CLASS)
    private Long id;

    @Column(name = "tid", updatable = false)
    private Long tid;

    public Long getId() {
        return id;
    }

    public JpaTidEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getTid() {
        return tid;
    }

    public JpaTidEntity setTid(Long tid) {
        this.tid = tid;
        return this;
    }
}
