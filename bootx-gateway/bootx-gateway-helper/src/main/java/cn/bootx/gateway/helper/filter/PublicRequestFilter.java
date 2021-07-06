package cn.bootx.gateway.helper.filter;

import cn.bootx.gateway.helper.api.HelperFilter;
import cn.bootx.gateway.helper.context.RequestContext;
import cn.bootx.gateway.helper.domain.CheckState;
import cn.bootx.gateway.helper.properties.GatewayProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**   
* 公共接口的权限校验
* @author xxm  
* @date 2021/6/1 
*/
@Component
@RequiredArgsConstructor
public class PublicRequestFilter implements HelperFilter {

    private final GatewayProperties gatewayProperties;

    @Override
    public int filterOrder() {
        return 30;
    }

    @Override
    public boolean shouldFilter(RequestContext context) {
        String requestUri = context.request.uri;
        return (requestUri.contains("/" + gatewayProperties.getSkipPrefix() + "/") ||
                requestUri.endsWith("/" + gatewayProperties.getSkipPrefix()));
    }

    @Override
    public boolean run(RequestContext context) {
        context.response.setStatus(CheckState.SUCCESS_PUBLIC_ACCESS);
        context.response.setMessage("Have access to this 'publicAccess' interface, permission: " + context.getPermission());
        return false;
    }

}
