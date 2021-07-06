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
        return context.getCustomUserDetails().isAdmin();
    }

    @Override
    public boolean run(RequestContext context) {
        context.response.setStatus(CheckState.SUCCESS_ADMIN);
        context.response.setMessage("admin用户可以访问界面，用户名: "+ context.getCustomUserDetails().getName());
        return false;
    }
}
