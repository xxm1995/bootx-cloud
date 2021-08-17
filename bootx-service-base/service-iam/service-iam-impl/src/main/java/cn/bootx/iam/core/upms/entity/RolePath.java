package cn.bootx.iam.core.upms.entity;

import cn.bootx.common.core.function.EntityBaseFunction;
import cn.bootx.common.mybatisplus.base.MpIdEntity;
import cn.bootx.iam.dto.permission.RolePathDto;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**   
* 角色路径权限表
* @author xxm  
* @date 2020/5/11 22:25 
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName("iam_role_path")
@NoArgsConstructor
public class RolePath extends MpIdEntity implements EntityBaseFunction<RolePathDto> {

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
