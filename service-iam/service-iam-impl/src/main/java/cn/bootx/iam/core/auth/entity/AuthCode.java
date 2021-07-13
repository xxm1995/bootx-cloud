package cn.bootx.iam.core.auth.entity;

import cn.bootx.starter.jpa.base.JpaTidEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @author xxm
 * @date 2020/4/27 18:02
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Accessors(chain = true)
@Table(name = "ac_auth_code")
public class AuthCode extends JpaTidEntity {

    /** 账号id */
    private Long authId;

    /** 授权码 */
    private String authCode;

    /** 终端 */
    private String client;

    /** 创建时间  */
    private LocalDateTime createDate;

}
