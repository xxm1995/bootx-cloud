package cn.bootx.usercenter.client.feign;

import cn.bootx.usercenter.client.PermissionClient;
import cn.bootx.usercenter.dto.permission.PermissionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConditionalOnMissingBean(PermissionClient.class)
public class PermissionClientFeignImpl implements PermissionClient {

    @Autowired(required = false)
    private PermissionFeign permissionFeign;

    @Override
    public List<PermissionDto> findByMethodAndService(String method, String serviceName){
        return permissionFeign.findByMethodAndService(method,serviceName).getData();
    }
}
