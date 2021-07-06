package cn.bootx.gateway.helper.filter;

import cn.bootx.common.web.code.WebHeaderConst;
import cn.bootx.common.web.entity.CustomUserDetails;
import cn.bootx.gateway.helper.api.HelperFilter;
import cn.bootx.gateway.helper.context.RequestContext;
import cn.bootx.gateway.helper.domain.CheckState;
import cn.bootx.gateway.helper.properties.GatewayHelperProperties;
import cn.bootx.gateway.helper.util.ServerRequestUtils;
import cn.bootx.starter.redis.RedisClient;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

/**
 * Api防重放
 * @author xxm
 * @date 2021/6/4
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ApiReplayFilter implements HelperFilter {
    private final GatewayHelperProperties gatewayHelperProperties;
    private final RedisClient redisClient;


    private final AntPathMatcher matcher = new AntPathMatcher();

    @Override
    public int filterOrder() {
        return 41;
    }

    @Override
    public boolean shouldFilter(RequestContext context) {
        GatewayHelperProperties.Filter.ApiReplay apiReplay = gatewayHelperProperties.getFilter().getApiReplay();
        // 是否开启
        if (!apiReplay.isEnabled()) {
            return false;
        }
        // 非get请求
        if (context.getRequest().getMethod() == HttpMethod.GET){
            return false;
        }
        // 是否属于不拦截路径
        if (apiReplay.getSkipPaths().stream().anyMatch(t -> matcher.match(t, context.request.uri))) {
            return false;
        }
        // 未登录
        CustomUserDetails userDetails = context.getCustomUserDetails();

        return userDetails != null;
    }

    @Override
    public boolean run(RequestContext context) {
        GatewayHelperProperties.Filter.ApiReplay apiReplay = gatewayHelperProperties.getFilter().getApiReplay();

        String opToken = this.getOpToken(context);
        // 没有opToken不进行处理
        if (StrUtil.isBlank(opToken)){
            return true;
        }
        String redisKey = "gateway:api-replay:" + opToken;

        // opToken存在报错 这样是为了避免发生并发和原子问题
        boolean lock = Boolean.TRUE.equals(redisClient.setIfAbsent(redisKey, "lock", apiReplay.getTimeoutOfOpToken()));
        if (!lock) {
            context.response.setStatus(CheckState.REQUEST_REPEAT);
            context.response.setMessage("重复请求，不要重复提交");
            return false;
        }
        return true;
    }

    /**
     * 获取opToken
     */
    private String getOpToken(RequestContext context) {
        ServerHttpRequest req = context.getServletRequest();
        String accessToken = ServerRequestUtils.resolveHeader(req, WebHeaderConst.OP_TOKEN);
        if(StrUtil.isBlank(accessToken)){
            accessToken = ServerRequestUtils.resolveParam(req, WebHeaderConst.OP_TOKEN);
        }
        return accessToken;
    }

}
