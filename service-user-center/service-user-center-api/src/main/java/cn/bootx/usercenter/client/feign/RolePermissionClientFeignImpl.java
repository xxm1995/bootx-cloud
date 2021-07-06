package cn.bootx.usercenter.client.feign;

import cn.bootx.usercenter.client.RolePermissionClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConditionalOnMissingBean(RolePermissionClient.class)
public class RolePermissionClientFeignImpl implements RolePermissionClient {

    @Autowired(required = false)
    private RolePermissionFeign rolePermissionFeign;
    @Override
    public List<Long> findPermissionIdsByRole(Long roleId) {
        return rolePermissionFeign.findPermissionIdsByRole(roleId).getData();
    }

    @Override
    public List<Long> findPermissionIdsByRoles(List<Long> roleIds) {
        return rolePermissionFeign.findPermissionIdsByRoles(roleIds).getData();
    }

    @Override
    public List<Long> findPermissionIdsByUser(Long userId){
        return rolePermissionFeign.findPermissionIdsByUser(userId).getData();
    }
}
