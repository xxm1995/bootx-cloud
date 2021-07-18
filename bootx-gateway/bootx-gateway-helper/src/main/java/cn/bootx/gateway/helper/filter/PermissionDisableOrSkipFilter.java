package cn.bootx.gateway.helper.filter;

import cn.bootx.gateway.helper.api.HelperFilter;
import cn.bootx.gateway.helper.properties.GatewayHelperProperties;
import cn.bootx.gateway.helper.context.RequestContext;
import cn.bootx.gateway.helper.domain.CheckState;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

/**   
* 是否启用权限校验和跳过权限校验路径的过滤器
* @author xxm  
* @date 2021/6/1 
*/
@Component
@RequiredArgsConstructor
public class PermissionDisableOrSkipFilter implements HelperFilter {

    private final GatewayHelperProperties gatewayHelperProperties;
    private final AntPathMatcher matcher = new AntPathMatcher();

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean run(RequestContext context) {
        // OPTIONS 直接放行
        if (context.getRequest().getMethod()== HttpMethod.OPTIONS){
            context.response.setStatus(CheckState.SUCCESS_SKIP_PATH);
            return false;
        }

        // TRACE 直接放行
        if (context.getRequest().getMethod()== HttpMethod.TRACE){
            context.response.setStatus(CheckState.SUCCESS_SKIP_PATH);
            return false;
        }
        // 禁用权限
        if (!gatewayHelperProperties.getPermission().getEnabled()) {
            context.response.setStatus(CheckState.SUCCESS_PERMISSION_DISABLED);
            context.response.setMessage("权限检查已禁用");
            return false;
        }

        // 内部
        GatewayHelperProperties.Permission permission = gatewayHelperProperties.getPermission();
        if (permission.getInternalPaths().stream().anyMatch(t -> matcher.match(t, context.request.uri))) {
            context.response.setStatus(CheckState.PERMISSION_WITH_IN);
            context.response.setMessage("无法访问内部界面");
            return false;
        }

        // 跳过路径
        if (permission.getSkipPaths().stream().anyMatch(t -> matcher.match(t, context.request.uri))) {
            context.response.setStatus(CheckState.SUCCESS_SKIP_PATH);
            return false;
        }
        return true;
    }
}
