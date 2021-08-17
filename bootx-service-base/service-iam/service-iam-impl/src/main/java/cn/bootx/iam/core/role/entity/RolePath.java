package cn.bootx.iam.core.role.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.iam.dto.permission.RolePathDto;
import cn.bootx.common.jpa.base.JpaTidEntity;
import cn.hutool.core.bean.BeanUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;

/**   
* 角色路径权限表
* @author xxm  
* @date 2020/5/11 22:25 
*/
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Accessors(chain = true)
@Table(name="uc_role_path")
@NoArgsConstructor
public class RolePath extends JpaTidEntity implements EntityBaseFunction<RolePathDto> {

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 权限id
     */
    private Long pathId;

   	public RolePath(Long roleId, Long pathId) {
   		this.roleId = roleId;
   		this.pathId = pathId;
   	}

    @Override
    public RolePathDto toDto() {
        RolePathDto rolePermissionDto = new RolePathDto();
        BeanUtil.copyProperties(this,rolePermissionDto);
        return rolePermissionDto;
    }
}
