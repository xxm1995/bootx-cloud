package cn.bootx.gateway.helper.service;

import cn.bootx.gateway.helper.domain.Permission;
import cn.bootx.iam.client.RolePathClient;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.spring.SpringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;


/**
* 权限应用服务
* @author xxm
* @date 2021/6/1
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class PermissionCheckService {
    private RolePathClient rolePathClient;
    private final ExecutorService asyncExecutorService;

    private void init(){
        if (Objects.isNull(rolePathClient)){
            rolePathClient = SpringUtil.getBean(RolePathClient.class);
        }
    }

    /**
     * 判断是否
     */
    public String check(Permission permission, Long userId){
        List<Long> permissionIds;
        this.init();
        // 异步转同步(filter中无法使用同步阻塞方法)
        try {
            permissionIds = asyncExecutorService.submit(() -> rolePathClient.findPermissionIdsByUser(userId)).get();
        } catch (InterruptedException | ExecutionException e) {
            log.warn("获取权限信息失败",e);
            return "获取权限信息失败，请稍后重试";
        }
        // 判断是否有权限
        boolean contains = CollUtil.contains(permissionIds, permission.getId());
        return contains?null:"该登录用户没有此接口访问权限";
    }

}
