package cn.bootx.usercenter.client.feign;

import cn.bootx.usercenter.client.UserRoleClient;
import cn.bootx.usercenter.dto.role.RoleDto;
import cn.bootx.usercenter.dto.user.UserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConditionalOnMissingBean(UserRoleClient.class)
public class UserRoleClientFeignImpl implements UserRoleClient {

    @Autowired(required = false)
    private UserRoleFeign userRoleFeign;

    @Override
    public List<RoleDto> findRolesByUser(Long userId) {
        return userRoleFeign.findRolesByUser(userId).getData();
    }

    @Override
    public List<Long> findRoleIdsByUser(Long userId) {
        return userRoleFeign.findRoleIdsByUser(userId).getData();
    }

    @Override
    public List<UserInfoDto> findUsersByRole(Long roleId) {
        return userRoleFeign.findUsersByRole(roleId).getData();
    }

    @Override
    public List<Long> findUserIdsByRole(Long roleId) {
        return userRoleFeign.findUserIdsByRole(roleId).getData();
    }
}
