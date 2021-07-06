package cn.bootx.authcenter.config;

import cn.bootx.bsp.client.feign.BspFeign;
import cn.bootx.usercenter.client.feign.UserCenterFeign;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackageClasses = {UserCenterFeign.class, BspFeign.class})
public class FeignConfig {
}
