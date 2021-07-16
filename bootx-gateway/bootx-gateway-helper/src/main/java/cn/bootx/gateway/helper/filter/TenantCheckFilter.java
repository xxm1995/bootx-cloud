package cn.bootx.gateway.helper.filter;

import cn.bootx.gateway.helper.api.HelperFilter;
import cn.bootx.gateway.helper.context.RequestContext;
import cn.bootx.gateway.helper.properties.GatewayHelperProperties;
import cn.bootx.gateway.helper.service.TenantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 检查租户信息
 * @author xxm
 * @date 2021/7/6
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TenantCheckFilter implements HelperFilter {
    private final TenantService tenantService;
    private final GatewayHelperProperties gatewayHelperProperties;

    @Override
    public int filterOrder() {
        return 15;
    }

    @Override
    public boolean shouldFilter(RequestContext context) {
        return gatewayHelperProperties.getFilter().getTenant().isEnabled();
    }

    @Override
    public boolean run(RequestContext context) {
        TenantService.TenantResult tenantResult = tenantService.checkTenant();
        if (tenantResult.isFlag()){
            return true;
        }
        context.response.setStatus(tenantResult.getState())
                .setMessage(tenantResult.getMessage());
        return false;
    }
}
