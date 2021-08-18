package cn.bootx.iam.config;

import cn.bootx.baseapi.client.feign.BaseApiFeign;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackageClasses = {BaseApiFeign.class})
public class FeignConfig {
}
