package cn.bootx.usercenter.config;

import cn.bootx.bsp.client.feign.BspFeign;
import cn.bootx.usercenter.client.feign.UserCenterFeign;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
*
* @author xxm
* @date 2021/4/11
*/
@Configuration
@EnableFeignClients(basePackageClasses = {BspFeign.class, UserCenterFeign.class})
public class FeignConfig {
}
