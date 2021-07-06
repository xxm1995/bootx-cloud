package cn.bootx.usercenter.client;

import cn.bootx.usercenter.dto.user.UserInfoDto;
import cn.bootx.usercenter.dto.role.RoleDto;

import java.util.List;

/**
* 用户角色关系
* @author xxm
* @date 2020/5/26 22:45
*/
public interface UserRoleClient {

    /**
     * 根据用户ID获取到已有角色集合
     */
    List<RoleDto> findRolesByUser(Long userId);

    /**
     * 根据用户ID获取到已有角色id集合
     */
    List<Long> findRoleIdsByUser(Long userId);

    /**
     * 根据角色ID获取到用户集合
     */
    List<UserInfoDto> findUsersByRole(Long roleId);

    /**
     * 根据角色ID获取到用户集合
     */
    List<Long> findUserIdsByRole(Long roleId);
}
