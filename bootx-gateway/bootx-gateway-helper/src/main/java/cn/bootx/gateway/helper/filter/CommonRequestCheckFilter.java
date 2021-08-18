package cn.bootx.gateway.helper.filter;

import cn.bootx.gateway.helper.api.HelperFilter;
import cn.bootx.gateway.helper.context.RequestContext;
import cn.bootx.gateway.helper.domain.CheckState;
import cn.bootx.gateway.helper.domain.Permission;
import cn.bootx.gateway.helper.properties.GatewayHelperProperties;
import cn.bootx.gateway.helper.service.PermissionCheckService;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 通用请求权限检查过滤器
 * @author xxm
 * @date 2021/6/7
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CommonRequestCheckFilter implements HelperFilter {

    private final GatewayHelperProperties properties;
    private final PermissionCheckService permissionCheckService;

    @Override
    public int filterOrder() {
        return 80;
    }

    @Override
    public boolean shouldFilter(RequestContext context) {
        boolean enable = properties.getFilter().getCommonRequest().isEnabled();
        if (!enable){
            context.response.setStatus(CheckState.SUCCESS_PASS_SITE);
            context.response.setMessage("不启用通用请求检查.");
        }
        return enable;
    }

    @Override
    public boolean run(RequestContext context) {
        Permission permission = context.getPermission();
        Long userId = context.getUserDetail().getId();

        String checkMessage = permissionCheckService.check(permission, userId);
        // 通过
        if (StrUtil.isNotBlank(checkMessage)){
            context.getResponse().setStatus(CheckState.PERMISSION_NOT_PASS)
                    .setMessage(checkMessage);
            return false;
        }
        context.getResponse().setStatus(CheckState.SUCCESS_PASS_SITE);
        return true;
    }
}
