package cn.bootx.bsp.core.tenant.entity;

import cn.bootx.bsp.core.tenant.convert.TenantConvert;
import cn.bootx.bsp.dto.tenant.TenantDto;
import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.common.core.code.CommonCode;
import cn.bootx.starter.jpa.JpaConst;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
* 租户
* @author xxm
* @date 2020/4/16 21:46
*/

@Data
@Accessors(chain = true)
@Entity
@Table(name = "bsp_tenant")
@SQLDelete(sql = "update bsp_tenant set deleted=" + CommonCode.DELETE_FLAG + " where id=? and version=?")
@Where(clause = "deleted=" + CommonCode.NORMAL_FLAG)
public class Tenant implements EntityBaseFunction<TenantDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = JpaConst.SNOWFLAKE_ID_STRATEGY)
    @GenericGenerator(name = JpaConst.SNOWFLAKE_ID_STRATEGY, strategy = JpaConst.SNOWFLAKE_ID_STRATEGY_CLASS)
    private Long id;

    /** 租户名称 唯一 */
    private String name;

    /** 租户code 唯一 */
    private String code;

    /** 租户展示名称 */
    private String displayName;

    /** 激活状态 */
    private Integer activeState = 0;

    /** 激活时间 */
    private LocalDateTime activeDate;

    /** 联系人 */
    private String contact;

    /** 联系电话 */
    private String phone;

    /** 分机号 */
    private String extension;

    /** 联系邮箱 */
    private String email;

    /** 通知环境(正式/测试) */
    private Integer noticeEnv;

    /** 加密模式 */
    private Integer encryptModel = 0;

    /** 允许清除 */
    private Boolean allowClear;

    private String dotNumber;

    private String city;

    private String country;

    private String zipCode;

    private String addition;

    /** 创建者ID */
    @Column(updatable = false)
    @CreatedBy
    private Long creator;

    /** 创建时间 */
    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime createTime;

    /** 最后修者ID */
    @LastModifiedBy
    private Long lastModifier;

    /** 最后修改时间 */
    @LastModifiedDate
    private LocalDateTime lastModifiedTime;

    /** 删除标志 */
    private boolean deleted;

    /** 乐观锁 */
    @Version
    private Integer version;


    public static Tenant init(TenantDto dto) {
        return TenantConvert.CONVERT.convert(dto);
    }

    @Override
    public TenantDto toDto() {
        TenantDto tenantDto = new TenantDto();
        return TenantConvert.CONVERT.convert(this);
    }

}