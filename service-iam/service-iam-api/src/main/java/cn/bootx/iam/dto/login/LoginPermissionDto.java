package cn.bootx.iam.dto.login;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel("权限")
public class LoginPermissionDto {
    /** 权限编码 */
    private String action;

    private String describe;
}
