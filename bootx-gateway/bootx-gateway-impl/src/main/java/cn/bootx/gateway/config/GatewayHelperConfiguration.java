package cn.bootx.gateway.config;

import cn.bootx.gateway.helper.properties.GatewayHelperProperties;
import cn.bootx.gateway.helper.properties.GatewayProperties;
import cn.bootx.gateway.helper.properties.MaintainProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.stream.Collectors;

/**
* 网关helper配置
* @author xxm
* @date 2021/6/1
*/
@Configuration
@EnableConfigurationProperties({GatewayHelperProperties.class, MaintainProperties.class, GatewayProperties.class})
public class GatewayHelperConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public HttpMessageConverters messageConverters(ObjectProvider<HttpMessageConverter<?>> converters) {
        return new HttpMessageConverters(converters.orderedStream().collect(Collectors.toList()));
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(ObjectMapper objectMapper) {
        return new MappingJackson2HttpMessageConverter(objectMapper);
    }
}
