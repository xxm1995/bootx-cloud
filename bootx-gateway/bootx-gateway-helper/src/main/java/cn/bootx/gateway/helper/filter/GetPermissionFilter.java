package cn.bootx.gateway.helper.filter;

import cn.bootx.gateway.helper.api.HelperFilter;
import cn.bootx.gateway.helper.context.RequestContext;
import cn.bootx.gateway.helper.domain.CheckState;
import cn.bootx.gateway.helper.domain.Permission;
import cn.bootx.gateway.helper.properties.GatewayHelperProperties;
import cn.bootx.gateway.helper.service.PermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 根据接口uri，method和service获取匹配到的权限
 * 匹配不到或者接口类型为内部接口，返回失败，不再向下执行
 * @author xxm
 * @date 2021/6/7
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class GetPermissionFilter implements HelperFilter {
    private final PermissionService permissionService;
    private final GatewayHelperProperties properties;

    @Override
    public int filterOrder() {
        return 20;
    }

    @Override
    public boolean shouldFilter(RequestContext context) {
        return properties.getFilter().getCommonRequest().isEnabled();
    }

    @Override
    public boolean run(RequestContext context) {
        // 根据请求获取对应的权限
        Permission permission = permissionService.selectPermissionByRequest(context.getRequestKey());
        if (permission == null) {
            context.response.setStatus(CheckState.PERMISSION_MISMATCH);
            context.response.setMessage("此请求与任何权限不匹配");
            return false;
        } else if (permission.getWithin()) {
            context.response.setStatus(CheckState.PERMISSION_WITH_IN);
            context.response.setMessage("无法访问内部界面");
            return false;
        } else {
            context.setPermission(permission);
        }
        return true;
    }
}
