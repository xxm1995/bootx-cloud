package cn.bootx.noticecenter.core.mail.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.noticecenter.dto.mail.MailTemplateDto;
import cn.bootx.common.jpa.base.JpaBaseEntity;
import cn.hutool.core.bean.BeanUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
* 邮件模板
* @author xxm
* @date 2020/6/8 21:52
*/
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "nc_mail_template")
@Accessors(chain = true)
@Data
public class MailTemplate extends JpaBaseEntity implements EntityBaseFunction<MailTemplateDto> {
    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 内容
     */
    private String date;

    /**
     * 路径
     */
    private String path;

    /**
     * 类型
     */
    private Integer type;

    public static MailTemplate init(MailTemplateDto dto){
        MailTemplate entity = new MailTemplate();
        BeanUtil.copyProperties(dto,entity);
        return entity;
    }

    @Override
    public MailTemplateDto toDto() {
        MailTemplateDto mailTemplateDto = new MailTemplateDto();
        BeanUtil.copyProperties(this,mailTemplateDto);
        return mailTemplateDto;
    }
}
