package cn.bootx.starter.jpa;

import cn.bootx.starter.snowflake.SnowFlakeId;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.Objects;

/**
 * Jpa雪花ID生成器
 * @author xxm
 * @date 2020/8/27
 */
public class SnowflakeIdStrategy implements IdentifierGenerator {

    private static SnowFlakeId snowFlakeId;

    public SnowflakeIdStrategy(){
        // do noting
    }

    SnowflakeIdStrategy(SnowFlakeId snowFlakeId) {
        SnowflakeIdStrategy.snowFlakeId = snowFlakeId;
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor s, Object o) throws HibernateException {
        Serializable id = s.getEntityPersister(null, o)
                .getClassMetadata()
                .getIdentifier(o, s);
        // 如果主键已经有值,使用原值
        if (Objects.nonNull(id)){
            return id;
        }
        return snowFlakeId.nextId();
    }

    @Override
    public boolean supportsJdbcBatchInserts() {
        return true;
    }
}
