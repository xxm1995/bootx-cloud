package cn.bootx.baseapi.dto.captcha;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@ApiModel("验证码数据")
public class CaptchaDataResult implements Serializable {
    private static final long serialVersionUID = 5086411715605406467L;

    @ApiModelProperty("验证码标示")
    private String captchaKey;

    @ApiModelProperty("验证码base64数据")
    private String captchaData;
}
