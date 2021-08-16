package cn.bootx.starter.headerholder;

import cn.bootx.starter.headerholder.holder.DefaultHeaderHolder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**   
* 请求头获取类
* @author xxm  
* @date 2020/4/15 14:58
*/
@Configuration
@EnableConfigurationProperties(HeaderProperties.class)
public class HeaderHolderAutoConfiguration {

	@Bean(name = "headerHolder")
    @ConditionalOnProperty(prefix = "bootx.starter.tenant",name = "single",havingValue="false")
	public HeaderHolder defaultHeaderHolder(){
		return new DefaultHeaderHolder();
	}

}
