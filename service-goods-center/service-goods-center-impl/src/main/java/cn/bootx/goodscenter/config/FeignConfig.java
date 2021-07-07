package cn.bootx.goodscenter.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
* @author xxm
* @date 2021/4/11
*/
@Configuration
@EnableFeignClients(basePackageClasses = {})
public class FeignConfig {
}
