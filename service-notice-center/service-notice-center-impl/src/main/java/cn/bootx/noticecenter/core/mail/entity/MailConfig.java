package cn.bootx.noticecenter.core.mail.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.noticecenter.dto.mail.MailConfigDto;
import cn.bootx.starter.jpa.base.JpaBaseEntity;
import cn.hutool.core.bean.BeanUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
* 邮件配置
* @author xxm  
* @date 2020/4/8 11:14 
*/
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Table(name = "nc_mail_config")
public class MailConfig extends JpaBaseEntity implements EntityBaseFunction<MailConfigDto> {

    /** 编号 */
    private String code;

    /** 名称 */
    private String name;

    /** 邮箱服务器 host */
    private String host;

    /** 邮箱服务器 port */
    private Integer port;

    /** "邮箱服务器 username */
    private String username;

    /** 邮箱服务器 password */
    private String password;

    /** 邮箱服务器 sender */
    private String sender;

    /** 邮箱服务器 from */
    @Column(name = "from_")
    private String from;

    /** 是否默认配置 */
    private Boolean isDefault;

    /** 安全方式 */
    private Integer securityType = 1;

    public static MailConfig init(MailConfigDto dto) {
        MailConfig entity = new MailConfig();
        BeanUtil.copyProperties(dto, entity);
        return entity;
    }

    @Override
    public MailConfigDto toDto() {
        MailConfigDto dto = new MailConfigDto();
        BeanUtil.copyProperties(this, dto);
        return dto;
    }
}
