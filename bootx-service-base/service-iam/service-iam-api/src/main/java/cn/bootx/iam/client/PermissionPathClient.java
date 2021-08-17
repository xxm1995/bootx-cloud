package cn.bootx.iam.client;


import cn.bootx.iam.dto.permission.PermissionPathDto;

import java.util.List;

/**
* 请求权限资源
* @author xxm  
* @date 2020/5/26 22:52 
*/
public interface PermissionPathClient {

    /**
     * 根据http方式和服务名进行查询
     */
    List<PermissionPathDto> findByMethodAndService(String method, String serviceName);
}
