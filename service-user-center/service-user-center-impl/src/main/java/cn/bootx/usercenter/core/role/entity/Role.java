package cn.bootx.usercenter.core.role.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.starter.jpa.base.JpaBaseEntity;
import cn.bootx.usercenter.core.role.convert.RoleConvert;
import cn.bootx.usercenter.dto.role.RoleDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;

/**   
* 角色
* @author xxm  
* @date 2020/5/1 17:31 
*/
@Entity
@Table(name="uc_role")
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class Role extends JpaBaseEntity implements EntityBaseFunction<RoleDto> {

    /** 编码 */
    private String code;

    /** 名称 */
    private String name;

    /** 描述 */
    private String description;

    public static Role init(RoleDto roleDto){
        return RoleConvert.CONVERT.convert(roleDto);
    }

    @Override
    public RoleDto toDto() {
        return RoleConvert.CONVERT.convert(this);
    }
}
