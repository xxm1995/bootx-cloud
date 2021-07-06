package cn.bootx.usercenter.core.role.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.starter.jpa.base.JpaTidEntity;
import cn.bootx.usercenter.dto.permission.RolePermissionDto;
import cn.hutool.core.bean.BeanUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;

/**   
* 角色权限表
* @author xxm  
* @date 2020/5/11 22:25 
*/
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Accessors(chain = true)
@Table(name="uc_role_permission")
@NoArgsConstructor
public class RolePermission extends JpaTidEntity implements EntityBaseFunction<RolePermissionDto> {

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 权限id
     */
    private Long permissionId;

   	public RolePermission(Long roleId, Long permissionId) {
   		this.roleId = roleId;
   		this.permissionId = permissionId;
   	}

    @Override
    public RolePermissionDto toDto() {
        RolePermissionDto rolePermissionDto = new RolePermissionDto();
        BeanUtil.copyProperties(this,rolePermissionDto);
        return rolePermissionDto;
    }
}
