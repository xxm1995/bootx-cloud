package cn.bootx.gateway.helper.filter;

import cn.bootx.gateway.helper.api.HelperFilter;
import cn.bootx.gateway.helper.context.RequestContext;
import cn.bootx.gateway.helper.domain.CommonRoute;
import cn.bootx.gateway.helper.domain.Permission;
import cn.bootx.gateway.helper.domain.TranceSpan;
import cn.bootx.gateway.helper.properties.GatewayHelperProperties;
import cn.hutool.core.thread.ThreadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


/**
* 缓存统计信息
* @author xxm
* @date 2021/6/8
*/
@Component
@RequiredArgsConstructor
public class CollectSpanFilter implements HelperFilter {

    private final StringRedisTemplate stringRedisTemplate;
    private final GatewayHelperProperties properties;

    @Override
    public int filterOrder() {
        return 25;
    }

    @Override
    public boolean shouldFilter(RequestContext context) {
        return properties.getFilter().getCollectSpan().isEnabled();
    }

    @Override
    public boolean run(RequestContext context) {
        CommonRoute route = context.getRoute();
        String serviceId = route.getServiceId();
        String method = context.request.method.name();
        String path = Optional.ofNullable(context.getPermission()).map(Permission::getPath)
                .orElse(context.getTrueUri());

        TranceSpan tranceSpan = new TranceSpan(path, serviceId, method, LocalDate.now());
        ThreadUtil.execAsync(() -> this.tranceSpanSubscriber(tranceSpan));
        return true;
    }

    private void tranceSpanSubscriber(final TranceSpan tranceSpan) {
        // 服务访问次数计数
        this.staticInvokeCount(tranceSpan.getServiceInvokeKey(), tranceSpan.getServiceInvokeValue());
        // api访问次数计数
        this.staticInvokeCount(tranceSpan.getApiInvokeKey(), tranceSpan.getApiInvokeValue());
    }

    private void staticInvokeCount(String key, String value) {
        if (Boolean.TRUE.equals(stringRedisTemplate.hasKey(key))) {
            stringRedisTemplate.opsForZSet().incrementScore(key, value, 1);
        } else {
            stringRedisTemplate.opsForZSet().add(key, value, 1);
            stringRedisTemplate.expire(key, 31, TimeUnit.DAYS);
        }
    }
}
