package cn.bootx.usercenter.dto.permission;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author xxm
* @date 2020/5/27 16:11
*/
@Data
@Accessors(chain = true)
@ApiModel("角色权限关系")
public class RolePermissionDto implements Serializable {
    private static final long serialVersionUID = -8448269114257637528L;

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("角色id")
    private Long roleId;

    @ApiModelProperty("权限id")
    private Long permissionId;
}
