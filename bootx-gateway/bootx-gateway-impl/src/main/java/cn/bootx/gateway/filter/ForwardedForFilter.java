package cn.bootx.gateway.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;
import java.util.Optional;

/**   
* 添加 X-Forwarded-For 记录客户端真实IP
* @author xxm  
* @date 2021/6/1 
*/
@Order(-2)
@Slf4j
@RequiredArgsConstructor
@Component
public class ForwardedForFilter implements GlobalFilter {

    private static final String HTTP_X_FORWARDED_FOR = "X-Forwarded-For";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String remoteAddress = Optional.ofNullable(request.getRemoteAddress())
                .map(InetSocketAddress::getHostString)
                .orElse("");
        return chain.filter(
                exchange.mutate()
                        .request(builder -> builder.header(HTTP_X_FORWARDED_FOR, remoteAddress))
                        .build());
    }
}
