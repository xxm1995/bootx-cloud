package cn.bootx.starter.snowflake;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
* 雪花id
* @author xxm
* @date 2020/9/26
*/
@Slf4j
@Configuration
@EnableConfigurationProperties(SnowFlakeProperties.class)
@ConditionalOnClass(SnowFlakeId.class)
public class SnowFlakeAutoConfiguration {
    private final SnowFlakeProperties snowFlakeProperties;

    public SnowFlakeAutoConfiguration(SnowFlakeProperties snowFlakeProperties) {
        this.snowFlakeProperties = snowFlakeProperties;
    }

    @Bean
    public SnowFlakeId snowFlakeId() {
        long workerId = snowFlakeProperties.getWorkerId();
        if(workerId < 1){
            log.warn("workerId 未进行设置，可能导致id生成重复");
        }
        long datacenterId = snowFlakeProperties.getDatacenterId();
        if(datacenterId < 1){
            log.warn("datacenterId 未进行设置，可能导致id生成重复");
        }
        return new SnowFlakeId(snowFlakeProperties.getWorkerId(),snowFlakeProperties.getDatacenterId());
    }
}
