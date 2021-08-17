package cn.bootx.common.feign;

import cn.bootx.common.feign.idempotency.IdempotencyProviderInterceptor;
import feign.Feign;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * feign对应的web配置
 * @author xxm
 * @date 2021/3/22
 */
@Configuration
@ConditionalOnClass(value = {Feign.class,WebMvcConfigurer.class})
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@RequiredArgsConstructor
public class FeignWebMvcConfigurer implements WebMvcConfigurer {
    private final IdempotencyProviderInterceptor idempotencyProviderInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(idempotencyProviderInterceptor)
                // 拦截所有请求
                .addPathPatterns("/**");
    }
}
