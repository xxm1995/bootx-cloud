package cn.bootx.starter.tenant;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**   
* 租户配置
* @author xxm  
* @date 2021/1/2 
*/
@Configuration
@EnableConfigurationProperties(TenantProperties.class)
public class TenantAutoConfiguration {
}
