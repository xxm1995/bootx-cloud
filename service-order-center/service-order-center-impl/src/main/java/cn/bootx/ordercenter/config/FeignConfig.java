package cn.bootx.ordercenter.config;

import cn.bootx.salescenter.client.feign.SalesCenterFeign;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackageClasses = {SalesCenterFeign.class})
public class FeignConfig {
}
