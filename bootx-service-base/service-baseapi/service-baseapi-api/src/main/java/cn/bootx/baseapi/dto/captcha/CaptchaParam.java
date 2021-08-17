package cn.bootx.baseapi.dto.captcha;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author xxm
* @date 2020/5/24 14:53
*/
@Data
@Accessors(chain = true)
@ApiModel("验证码")
public class CaptchaParam implements Serializable{
    private static final long serialVersionUID = -8895769893066232989L;

    @ApiModelProperty("验证码标识")
    private String code;

    @ApiModelProperty("验证码")
    private String value;
}
