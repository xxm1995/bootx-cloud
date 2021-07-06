package cn.bootx.noticecenter.dto.mail;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**   
* 基础通知参数
* @author xxm  
* @date 2020/5/2 20:32 
*/
@Data
@Accessors(chain = true)
public class BaseMailParam implements Serializable {
    private static final long serialVersionUID = 5270841506064102447L;
    
    @ApiModelProperty(name = "configCode", value = "配置编号")
    private String configCode;

    @ApiModelProperty(name = "message", value = "消息", required = true)
    @NotNull(message = "消息不能为空")
    private String message;

    @ApiModelProperty("是否记录日志,默认为false")
    private Boolean withLog = false;

}
