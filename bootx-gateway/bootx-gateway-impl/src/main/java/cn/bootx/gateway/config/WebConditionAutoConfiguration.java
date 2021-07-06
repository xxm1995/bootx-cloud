package cn.bootx.gateway.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;

/**   
* 网关配置
* @author xxm  
* @date 2021/6/1 
*/
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
@Configuration
public class WebConditionAutoConfiguration {


}
