package cn.bootx.authcenter.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * AuthCode登陆
 * @author xxm
 * @date 2020/4/27 18:32
 */
@Data
@Accessors(chain = true)
public class AuthCodeLoginParam implements Serializable {
    private static final long serialVersionUID = 3218981564686315663L;

    @ApiModelProperty(value = "authCode", required = true)
    @NotNull(message = "authCode cannot be null")
    private String authCode;

    @ApiModelProperty(name = "client", value = "操作客户端", required = true)
    @NotNull(message = "client cannot be null")
    private String client;

}
