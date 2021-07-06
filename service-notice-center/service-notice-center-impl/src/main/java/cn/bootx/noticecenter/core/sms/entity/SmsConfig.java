package cn.bootx.noticecenter.core.sms.entity;

import cn.bootx.starter.jpa.base.JpaBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
* @author xxm
* @date 2020/4/8 13:19
*/
@Data
@Entity
@Table(name = "nc_sms_config")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SmsConfig extends JpaBaseEntity {
    private Long id;
    private String accountSid;
    private String authToken;
    private String pathSid;
    private String fromNum;
    private Boolean isDefault;
    private String secret;
    private String isp;
    private String replyMsg;
}
