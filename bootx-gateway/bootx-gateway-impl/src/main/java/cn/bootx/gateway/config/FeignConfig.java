package cn.bootx.gateway.config;

import cn.bootx.authcenter.client.feign.AuthCenterFeign;
import cn.bootx.bsp.client.feign.BspFeign;
import cn.bootx.common.web.code.WebHeaderConst;
import cn.bootx.starter.headerholder.local.HolderContextHolder;
import cn.bootx.usercenter.client.feign.UserCenterFeign;
import feign.RequestInterceptor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

/**   
* feign配置
* @author xxm  
* @date 2021/6/2 
*/
@Configuration
@EnableFeignClients(basePackageClasses = {AuthCenterFeign.class, UserCenterFeign.class, BspFeign.class})
public class FeignConfig {

    /**
     * 请求拦截器, 主要用于跨服务调用时传入某些参数，便于服务内部使用。
     */
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            String tid = HolderContextHolder.get(WebHeaderConst.TID);
            if (Objects.nonNull(tid)) {
                requestTemplate.header(WebHeaderConst.TID, tid);
            }
            String ACCESS_TOKEN = HolderContextHolder.get(WebHeaderConst.ACCESS_TOKEN);
            if (Objects.nonNull(ACCESS_TOKEN)) {
                requestTemplate.header(WebHeaderConst.ACCESS_TOKEN, ACCESS_TOKEN);
            }
        };
    }
}
