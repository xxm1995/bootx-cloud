package cn.bootx.iam.core.upms.entity;

import cn.bootx.common.mybatisplus.base.MpIdEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 角色菜单权限表
 * @author xxm
 * @date 2020/5/11 22:25
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName("iam_role_menu")
@NoArgsConstructor
public class RoleMenu extends MpIdEntity {

    private Long roleId;

    /**
     * 菜单权限id
     */
    private Long permissionId;

    public RoleMenu(Long roleId, Long permissionId) {
        this.roleId = roleId;
        this.permissionId = permissionId;
    }
}
