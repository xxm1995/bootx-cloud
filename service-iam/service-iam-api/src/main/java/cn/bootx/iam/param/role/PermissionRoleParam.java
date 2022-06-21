package cn.bootx.iam.param.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
* @author xxm
* @date 2021/6/9
*/
@Data
@Accessors(chain = true)
@ApiModel("角色权限")
public class PermissionRoleParam implements Serializable {
    private static final long serialVersionUID = 4344723093613370279L;

    @ApiModelProperty("权限ID")
    private Long permissionId;

    @ApiModelProperty("角色ids")
    private List<Long> roleIds;
}
