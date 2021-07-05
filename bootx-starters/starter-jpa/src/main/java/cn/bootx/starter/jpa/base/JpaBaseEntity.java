package cn.bootx.starter.jpa.base;

import cn.bootx.starter.jpa.audit.JpaEntityAuditListener;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.time.LocalDateTime;

/**   
* 基础实体类
* @author xxm  
* @date 2020/10/10
*/
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class, JpaEntityAuditListener.class})
public class JpaBaseEntity extends JpaTidEntity{

    /** 创建者ID */
    @Column(name = "creator", updatable = false)
    @CreatedBy
    private Long creator;

    /** 创建时间 */
    @Column(name = "create_time", updatable = false)
    @CreatedDate
    private LocalDateTime createTime;

    /** 最后修者ID */
    @Column(name = "last_modifier")
    @LastModifiedBy
    private Long lastModifier;

    /** 最后修改时间 */
    @Column(name = "last_modified_time")
    @LastModifiedDate
    private LocalDateTime lastModifiedTime;

    /** 删除标志 */
    private boolean deleted;

    /** 乐观锁 */
    @Version
    private Integer version;

    public Long getCreator() {
        return creator;
    }

    public JpaBaseEntity setCreator(Long creator) {
        this.creator = creator;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public JpaBaseEntity setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public Long getLastModifier() {
        return lastModifier;
    }

    public JpaBaseEntity setLastModifier(Long lastModifier) {
        this.lastModifier = lastModifier;
        return this;
    }

    public LocalDateTime getLastModifiedTime() {
        return lastModifiedTime;
    }

    public JpaBaseEntity setLastModifiedTime(LocalDateTime lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
        return this;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public JpaBaseEntity setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public JpaBaseEntity setVersion(Integer version) {
        this.version = version;
        return this;
    }
}
