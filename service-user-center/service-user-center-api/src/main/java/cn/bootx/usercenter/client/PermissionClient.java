package cn.bootx.usercenter.client;

import cn.bootx.usercenter.dto.permission.PermissionDto;

import java.util.List;

/**
* 权限资源(菜单/操作)
* @author xxm  
* @date 2020/5/26 22:52 
*/
public interface PermissionClient {

    List<PermissionDto> findByMethodAndService(String method, String serviceName);
}
