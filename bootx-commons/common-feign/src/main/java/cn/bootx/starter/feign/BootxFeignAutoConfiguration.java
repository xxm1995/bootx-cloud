package cn.bootx.starter.feign;

import cn.bootx.common.core.code.WebHeaderConst;
import cn.bootx.starter.feign.decoder.FeignResultDecoder;
import cn.bootx.starter.feign.decoder.ResponseInterceptor;
import cn.bootx.starter.feign.idempotency.IdempotencyProviderInterceptor;
import cn.bootx.starter.feign.interceptor.DefaultInterceptor;
import cn.bootx.starter.headerholder.HeaderHolder;
import cn.bootx.starter.idempotency.IdempotencyProperties;
import cn.bootx.starter.redis.RedisClient;
import cn.bootx.starter.snowflake.SnowFlakeId;
import feign.Feign;
import feign.RequestInterceptor;
import feign.codec.Decoder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

/**
 * feign自动化配置
 * @author xxm
 * @date 2021/3/22
 */
@Configuration
@ConditionalOnClass(Feign.class)
@RequiredArgsConstructor
public class BootxFeignAutoConfiguration {
    private final HeaderHolder headerHolder;
    private final RedisClient idemRedisClient;
    private final SnowFlakeId snowFlakeId;
    private final IdempotencyProperties idempotencyProperties;

    /**
     * 幂等性
     */
    @Bean
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
    public IdempotencyProviderInterceptor idempotencyOperationInterceptor() {
        return new IdempotencyProviderInterceptor(idemRedisClient, headerHolder, idempotencyProperties);
    }

    /**
     * feign 解码器
     */
    @Bean
    public Decoder feignDecoder(ObjectFactory<HttpMessageConverters> messageConverters, ResponseInterceptor responseInterceptor) {
        return new FeignResultDecoder(new SpringDecoder(messageConverters),responseInterceptor);
    }

    /**
     * 请求拦截器, 主要用于跨服务调用时传入某些参数，便于服务内部使用。
     */
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            // 租户
            Long tid = headerHolder.getTid();
            if (Objects.nonNull(tid)) {
                requestTemplate.header(WebHeaderConst.TID, String.valueOf(tid));
            }
            // jwt
            String jwtToken = headerHolder.getJwtToken();
            if(Objects.nonNull(jwtToken)){
                requestTemplate.header(WebHeaderConst.JWT_TOKEN,jwtToken);
            }
            // 审计
            Long operatorId = headerHolder.getOperatorId();
            if (Objects.nonNull(operatorId)) {
                requestTemplate.header(WebHeaderConst.OPERATOR_ID, String.valueOf(operatorId));
            }

            // 开启且为非幂等操作
            if (idempotencyProperties.isEnableConsumer()){
                //加入操作opToken的信息，便于内部服务进行幂等性控制
                String opToken = WebHeaderConst.OP_TOKEN+":"+snowFlakeId.nextIdStr();
                requestTemplate.header(WebHeaderConst.OP_TOKEN, opToken);
            }
        };
    }

    /**
     * 响应拦截器
     */
    @Bean
    @ConditionalOnMissingBean(ResponseInterceptor.class)
    public ResponseInterceptor responseInterceptor(){
        return new DefaultInterceptor();
    }

}
