package cn.bootx.gateway.helper.filter;

import cn.bootx.gateway.helper.api.HelperFilter;
import cn.bootx.gateway.helper.context.RequestContext;
import cn.bootx.gateway.helper.domain.CheckState;
import cn.bootx.gateway.helper.domain.UserDetailsWithResult;
import cn.bootx.gateway.helper.service.UserDetailsService;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 根据access_token获取对应的用户信息和角色信息
 * @author xxm
 * @date 2021/5/31
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class GetUserDetailsFilter implements HelperFilter {
    private final UserDetailsService userDetailsService;

    @Override
    public int filterOrder() {
        return 40;
    }

    @Override
    public boolean run(RequestContext context) {
        String accessToken = context.request.accessToken;
        // token是否存在
        if (StrUtil.isBlank(accessToken)) {
            context.response.setStatus(CheckState.PERMISSION_ACCESS_TOKEN_NULL);
            context.response.setMessage("AccessToken 为空");
            return false;
        }

        UserDetailsWithResult result = userDetailsService.getUserDetails(accessToken);
        if (result.getUserDetail() == null) {
            context.response.setStatus(result.getState());
            context.response.setMessage(result.getMessage());
            return false;
        }
        context.setUserDetail(result.getUserDetail());
        return true;
    }
}
