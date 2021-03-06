package cn.bootx.starter.auth.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
* 认证相关配置
* @author xxm
* @date 2021/7/30
*/
@Configuration
@EnableConfigurationProperties(AuthProperties.class)
public class AuthConfigure {
}
