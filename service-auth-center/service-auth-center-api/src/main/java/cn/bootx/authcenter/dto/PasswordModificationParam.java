package cn.bootx.authcenter.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 修改密码参数
 * <p>
 * <p>
 *
 * @author liuchenwei
 * @date 2018/1/29
 */
@ApiModel(value = "修改密码参数")
@Data
@Accessors(chain = true)
public class PasswordModificationParam implements Serializable {

    private static final long serialVersionUID = -2918842435173028493L;
    @ApiModelProperty(value = "用户 id", required = true)
    @NotNull(message = "uid cannot be null ")
    private Long uid;

    @ApiModelProperty(value = "旧密码", required = true)
    @NotNull(message = "old password cannot be null")
    private String passwordOld;

    @ApiModelProperty(value = "新密码", required = true)
    @NotNull(message = "new password cannot be null")
    private String passwordNew;

    @ApiModelProperty(name = "client", value = "操作客户端", required = true)
    @NotNull(message = "client cannot be null")
    private String client;
}
