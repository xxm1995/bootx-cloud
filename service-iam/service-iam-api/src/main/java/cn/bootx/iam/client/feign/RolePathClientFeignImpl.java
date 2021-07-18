package cn.bootx.iam.client.feign;

import cn.bootx.iam.client.RolePathClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConditionalOnMissingBean(RolePathClient.class)
public class RolePathClientFeignImpl implements RolePathClient {

    @Autowired(required = false)
    private RolePathFeign rolePermissionFeign;

    @Override
    public List<Long> findPermissionIdsByUser(Long userId){
        return rolePermissionFeign.findPermissionIdsByUser(userId).getData();
    }
}
