package cn.bootx.iam.core.role.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.iam.dto.permission.RoleMenuDto;
import cn.bootx.common.jpa.base.JpaTidEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 角色菜单权限表
 * @author xxm
 * @date 2020/5/11 22:25
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Accessors(chain = true)
@Table(name="uc_role_menu")
@NoArgsConstructor
public class RoleMenu extends JpaTidEntity implements EntityBaseFunction<RoleMenuDto> {

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 菜单权限id
     */
    private Long menuId;

    public RoleMenu(Long roleId, Long menuId) {
        this.roleId = roleId;
        this.menuId = menuId;
    }

    @Override
    public RoleMenuDto toDto() {
        return null;
    }
}
