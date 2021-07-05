package cn.bootx.starter.jpa.audit;

import cn.bootx.starter.headerholder.HeaderHolder;
import cn.bootx.starter.jpa.base.JpaTidEntity;
import lombok.RequiredArgsConstructor;

import javax.persistence.PrePersist;
import java.util.Objects;

/**
 * jpa实体类审计信息
 * 注意: 继承AuditingEntityListener后创建更新字段也不会填充
 * @author xxm
 * @date 2020/10/17
 */
@SuppressWarnings("JpaEntityListenerInspection")
@RequiredArgsConstructor
public class JpaEntityAuditListener {
    private final HeaderHolder headerHolder;

    /**
     * 设置租户id
     * 存储前设置
     */
    @PrePersist
    public void setTid(Object object) {
        if (object instanceof JpaTidEntity){
            JpaTidEntity entity = (JpaTidEntity) object;
            if (Objects.isNull(entity.getTid())) {
                entity.setTid(headerHolder.getTid());
            }
        }
    }

}
