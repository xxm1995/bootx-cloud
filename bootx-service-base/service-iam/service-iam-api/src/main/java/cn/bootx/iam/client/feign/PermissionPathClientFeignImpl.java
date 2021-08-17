package cn.bootx.iam.client.feign;

import cn.bootx.iam.client.PermissionPathClient;
import cn.bootx.iam.dto.permission.PermissionPathDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConditionalOnMissingBean(PermissionPathClient.class)
public class PermissionPathClientFeignImpl implements PermissionPathClient {

    @Autowired(required = false)
    private PermissionPathFeign permissionFeign;

    @Override
    public List<PermissionPathDto> findByMethodAndService(String method, String serviceName){
        return permissionFeign.findByMethodAndService(method,serviceName).getData();
    }
}
