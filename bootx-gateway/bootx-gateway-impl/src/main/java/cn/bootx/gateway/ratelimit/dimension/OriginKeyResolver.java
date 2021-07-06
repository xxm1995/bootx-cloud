package cn.bootx.gateway.ratelimit.dimension;

import cn.bootx.gateway.ratelimit.switcher.DoubleModeSwitcher;
import cn.bootx.gateway.ratelimit.switcher.ModeSwitcher;
import cn.bootx.gateway.ratelimit.switcher.SwitcherDelegate;
import cn.bootx.gateway.util.KeyGenerator;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**   
* 
* @author xxm  
* @date 2021/6/3 
*/
public class OriginKeyResolver implements KeyResolver, SwitcherDelegate {

    private static final String PREFIX = "origin";

    private ModeSwitcher modeSwitcher = new DoubleModeSwitcher();

    public OriginKeyResolver() {
        this.modeSwitcher.switchMode(null, null);
    }

    public OriginKeyResolver(String mode, String listString) {
        this.modeSwitcher.switchMode(mode, listString);
    }

    @Override
    public ModeSwitcher getModeSwitcher() {
        return modeSwitcher;
    }

    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        return Mono.just(Optional.ofNullable(getOrigin(exchange)).orElse(KeyGenerator.generate()));
    }

    private String getOrigin(ServerWebExchange exchange) {
        String originKey = getModeSwitcher().execute(exchange.getRequest().getHeaders().getOrigin());
        return originKey == null ? null : PREFIX + originKey;
    }

}
