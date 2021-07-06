package cn.bootx.noticecenter.dto.mail;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**   
* touser 必需时使用的参数
* @author xxm  
* @date 2020/5/2 20:32 
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public abstract class ToUserRequiredMailParam extends BaseMailParam implements Serializable {

    private static final long serialVersionUID = 248630938901130468L;

    @ApiModelProperty(name = "to", value = "接收人", required = true)
    @NotNull(message = "to 不能为空")
    private List<String> to;

}
