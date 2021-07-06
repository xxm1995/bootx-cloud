package cn.bootx.baseapi.config;

import cn.bootx.bsp.client.feign.BspFeign;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**   
* feign配置
* @author xxm  
* @date 2021/4/11 
*/
@EnableFeignClients(basePackageClasses = BspFeign.class)
@Configuration
public class FeignConfig {
}
