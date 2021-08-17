package cn.bootx.gateway.helper.api.reactive;

import cn.bootx.common.core.code.WebHeaderConst;
import cn.bootx.gateway.helper.api.HelperChain;
import cn.bootx.gateway.helper.context.RequestContext;
import cn.bootx.gateway.helper.context.ResponseContext;
import cn.bootx.gateway.helper.domain.CheckRequest;
import cn.bootx.gateway.helper.domain.CheckResponse;
import cn.bootx.gateway.helper.util.ServerRequestUtils;
import cn.bootx.common.headerholder.local.HolderContextHolder;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Optional;


/**
* 验证助手Reactive
* @author xxm
* @date 2021/5/31
*/
@Slf4j
@Component
@RequiredArgsConstructor
public class ReactiveAuthenticationHelper {
    private final HelperChain helperChain;

    /**
     * 验证
     */
    public ResponseContext  authentication(ServerWebExchange exchange) {
        // 初始化 请求上下文
        String accessToken = Optional.ofNullable(this.getAccessToken(exchange.getRequest()))
                .orElse(null);
        CheckRequest checkRequest = new CheckRequest(accessToken,
                exchange.getRequest().getURI().getPath(),
                exchange.getRequest().getMethod());
        RequestContext requestContext = RequestContext.initRequestContext(checkRequest, new CheckResponse());
        requestContext.setServletRequest(exchange.getRequest());
        return helperChain.doFilter(requestContext);
    }

    /**
     * 解析
     */
    private String getAccessToken(final ServerHttpRequest req) {
        String accessToken = ServerRequestUtils.resolveHeader(req, WebHeaderConst.ACCESS_TOKEN);
        if(StrUtil.isBlank(accessToken)){
            accessToken = ServerRequestUtils.resolveParam(req, WebHeaderConst.ACCESS_TOKEN);
        }
        // 添加access token请求
        HolderContextHolder.put(WebHeaderConst.ACCESS_TOKEN,accessToken);
        return accessToken;
    }
}
