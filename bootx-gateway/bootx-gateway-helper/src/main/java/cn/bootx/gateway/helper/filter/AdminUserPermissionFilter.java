package cn.bootx.gateway.helper.filter;

import cn.bootx.gateway.helper.api.HelperFilter;
import cn.bootx.gateway.helper.context.RequestContext;
import cn.bootx.gateway.helper.domain.CheckState;
import org.springframework.stereotype.Component;

/**   
* 超级管理员的权限校验
* @author xxm  
* @date 2021/6/1 
*/
@Component
public class AdminUserPermissionFilter implements HelperFilter {

    @Override
    public int filterOrder() {
        return 70;
    }

    @Override
    public boolean shouldFilter(RequestContext context) {
        return context.getUserDetail().isAdmin();
    }

    @Override
    public boolean run(RequestContext context) {
        context.response.setStatus(CheckState.SUCCESS_ADMIN);
        return false;
    }
}
