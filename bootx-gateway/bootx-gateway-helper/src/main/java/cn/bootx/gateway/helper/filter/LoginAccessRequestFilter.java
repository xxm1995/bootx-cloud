package cn.bootx.gateway.helper.filter;

import cn.bootx.gateway.helper.api.HelperFilter;
import cn.bootx.gateway.helper.context.RequestContext;
import cn.bootx.gateway.helper.domain.CheckState;
import org.springframework.stereotype.Component;

/**   
* loginAccess请求的权限校验
* @author xxm  
* @date 2021/6/1 
*/
@Component
public class LoginAccessRequestFilter implements HelperFilter {

    @Override
    public int filterOrder() {
        return 60;
    }

    @Override
    public boolean shouldFilter(RequestContext context) {
        return false;
    }

    @Override
    public boolean run(RequestContext context) {
        if (context.getCustomUserDetails() != null) {
            context.response.setStatus(CheckState.SUCCESS_LOGIN_ACCESS);
            context.response.setMessage("可以访问这个“loginAccess”界面，权限： " + context.getPermission());
            return false;
        }
        return true;
    }

}
