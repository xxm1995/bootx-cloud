package cn.bootx.gateway.helper.filter;

import cn.bootx.gateway.helper.api.HelperFilter;
import cn.bootx.gateway.helper.context.RequestContext;
import cn.bootx.gateway.helper.domain.CheckState;
import cn.bootx.gateway.helper.domain.CommonRoute;
import cn.bootx.gateway.helper.domain.RequestKey;
import cn.bootx.gateway.helper.resolver.GatewayPropertiesResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Map;

/**
 * 获取请求路由信息
 * @author xxm
 * @date 2021/6/7
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class GetRequestRouteFilter implements HelperFilter {
    private final GatewayProperties gatewayProperties;
    private final GatewayPropertiesResolver gatewayPropertiesResolver;

    private final AntPathMatcher matcher = new AntPathMatcher();

    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean run(RequestContext context) {
        String requestUri = context.request.uri;
        //根据请求uri获取route
        CommonRoute route = this.getRoute(requestUri);

        if (route == null) {
            context.response.setStatus(CheckState.PERMISSION_SERVICE_ROUTE);
            context.response.setMessage("此请求不与任何路由匹配，uri: " + requestUri);
            return false;
        }  else {
            String trueUri = this.getRequestTruePath(requestUri, route.getPath());
            context.setTrueUri(trueUri);
            context.setRoute(route);
            context.setRequestKey(new RequestKey()
                    .setUri(trueUri)
                    .setMethod(context.request.method.name().toLowerCase())
                    .setServiceName(route.getServiceId()));
            return true;
        }
    }

    /**
     * 获取请求真实路径
     */
    private String getRequestTruePath(String uri, String routePath) {
        return "/" + matcher.extractPathWithinPattern(routePath, uri);
    }

    /**
     * 获取路由信息
     */
    private CommonRoute getRoute(String requestUri) {
        Map<String, CommonRoute> routeMap = gatewayPropertiesResolver.resolveRoutes(gatewayProperties);
        for (CommonRoute route : routeMap.values()) {
            if (matcher.match(route.getPath(), requestUri)) {
                return route;
            }
        }
        return null;
    }
}
