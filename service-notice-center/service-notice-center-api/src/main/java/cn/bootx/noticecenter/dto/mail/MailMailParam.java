package cn.bootx.noticecenter.dto.mail;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**   
* 发邮件的参数
* @author xxm  
* @date 2020/5/2 20:31
*/
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "发邮件的参数")
@Data
@Accessors(chain = true)
public class MailMailParam extends ToUserRequiredMailParam implements Serializable {

    private static final long serialVersionUID = 7472189938984313186L;

    @ApiModelProperty(name = "subject", value = "标题", required = true)
    @NotNull(message = "主题不能为空")
    private String subject;

    @ApiModelProperty("抄送")
    private List<String> ccList;

    @ApiModelProperty("密送")
    private List<String> bccList;

    @ApiModelProperty("是否单条发送(拆分收件人)  默认true")
    private Boolean singleSend = true;

    @ApiModelProperty(name = "sendAttachment", value = "是否包含附件")
    private boolean sendAttachment;

    @ApiModelProperty(name = "fileList", value = "附件列表")
    private List<MailFileParam> fileList;
}
