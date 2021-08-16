package cn.bootx.starter.feign.idempotency;

import cn.bootx.common.core.annotation.Idempotent;
import cn.bootx.common.core.exception.RepetitiveOperationException;
import cn.bootx.starter.headerholder.HeaderHolder;
import cn.bootx.starter.idempotency.IdempotencyProperties;
import cn.bootx.starter.redis.RedisClient;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
* 幂等拦截器(服务端)
* @author xxm
* @date 2021/3/22
*/
@RequiredArgsConstructor
public class IdempotencyProviderInterceptor implements AsyncHandlerInterceptor {
    private final RedisClient idemRedisClient;
    private final HeaderHolder headerHolder;
    private final IdempotencyProperties idempotencyProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o) {

        if (this.needIdempotency(request,o)) {
            String opToken = headerHolder.getOpToken();

            // 未开启服务端验证
            if (!idempotencyProperties.isEnableProvider()){
                return true;
            }

            // 没有opToken,意味着不是 Feign 的调用方式或者不需要进行幂等处理
            if (StrUtil.isBlank(opToken)) {
                return true;
            }

            // opToken存在报错 这样是为了避免发生并发和原子问题
            boolean lock = Boolean.TRUE.equals(idemRedisClient.setIfAbsent(opToken, "lock",idempotencyProperties.getTimeoutOfOpToken()));
            if (!lock) {
                throw new RepetitiveOperationException();
            }
        }
        return true;
    }

    /**
     * 判断是否启用幂等处理
     */
    private boolean needIdempotency(HttpServletRequest request,Object o) {
        String servletPath = request.getServletPath();
        if (Objects.equals("/error",servletPath)){
            return false;
        }
        // 先判断 Idempotent Annotation
        if (o instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) o;
            Idempotent methodAnnotation = handlerMethod.getMethodAnnotation(Idempotent.class);
            if (Objects.nonNull(methodAnnotation)){
                return methodAnnotation.enable();
            }
        }
        // 默认GET请求不进行幂等校验
        HttpMethod httpMethod = HttpMethod.resolve(request.getMethod());
        return HttpMethod.POST == httpMethod || HttpMethod.PUT == httpMethod || HttpMethod.DELETE == httpMethod;
    }

}
