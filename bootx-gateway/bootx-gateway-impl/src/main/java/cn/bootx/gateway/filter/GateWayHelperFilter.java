package cn.bootx.gateway.filter;

import cn.bootx.common.core.code.WebHeaderConst;
import cn.bootx.common.core.rest.Res;
import cn.bootx.common.core.rest.ResResult;
import cn.bootx.gateway.helper.api.reactive.ReactiveAuthenticationHelper;
import cn.bootx.gateway.helper.context.ResponseContext;
import cn.bootx.gateway.helper.domain.CheckState;
import cn.bootx.starter.headerholder.local.HolderContextHolder;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;

import static cn.bootx.common.core.code.WebHeaderConst.JWT_TOKEN;

/**
 * 负责将HTTP请求去除消息体后转发到gateway helper去权限校验，限流
 * gateway helper返回后将授权码和label加到消息头部
 * 再交给gateway去路由到真实服务
 * @author xxm
 * @date 2021/5/31
 */
@Order(-1)
@Slf4j
@RequiredArgsConstructor
@Component
public class GateWayHelperFilter implements WebFilter {
    private final ReactiveAuthenticationHelper authenticationHelper;
    private final CorsWebFilter corsWebFilter;

    @Override
    @SuppressWarnings("NullableProblems")
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        ResponseContext responseContext = this.authentication(exchange);

        // 不需要认证的接口, 执行下一个过滤器
        if (StrUtil.equals(responseContext.getRequestCode(), CheckState.SUCCESS_SKIP_PATH.getCode())) {
            return chain.filter(exchange);
        }
        // 认证成功
        if (responseContext.getHttpStatus().is2xxSuccessful()) {
            String jwtToken = responseContext.getJwtToken();
            return chain.filter(exchange.mutate()
                    // 添加jwtToken
                    .request(builder -> builder.header(JWT_TOKEN, jwtToken))
                    .build());
        } else {
            // 处理下cors跨域问题
            corsWebFilter.filter(exchange,chain);
            return this.errorResponse(exchange.getResponse(), responseContext);
        }
    }

    /**
     * 验证
     */
    public ResponseContext authentication(ServerWebExchange exchange){

        // 获取请求头中的值,放到线程变量中
        HttpHeaders headers = exchange.getRequest().getHeaders();
        // tid
        List<String> tidList = headers.get(WebHeaderConst.TID);
        if (CollUtil.isNotEmpty(tidList)){
            //noinspection ConstantConditions
            HolderContextHolder.put(WebHeaderConst.TID,tidList.get(0));
        }

        // 进行认证并获取上下文
        ResponseContext responseContext = authenticationHelper.authentication(exchange);

        // 验证链执行完毕,清除线程变量
        HolderContextHolder.clear();

        return responseContext;
    }

    /**
     * 错误响应
     */
    private Mono<Void> errorResponse(ServerHttpResponse response, ResponseContext responseContext) {
        ResResult<String> error = Res.error(responseContext.getRequestMessage());
        response.setStatusCode(responseContext.getHttpStatus());
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        DataBuffer dataBuffer = response.bufferFactory().wrap(JSONUtil.toJsonStr(error).getBytes());
        return response.writeWith(Mono.just(dataBuffer));
    }

}
