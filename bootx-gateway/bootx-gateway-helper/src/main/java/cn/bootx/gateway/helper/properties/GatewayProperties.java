package cn.bootx.gateway.helper.properties;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Collections;
import java.util.List;

/**
* 网关参数
* @author xxm
* @date 2021/6/1
*/
@Data
@Accessors(chain = true)
@ConfigurationProperties(prefix = "hzero.cn.bootx.gateway")
public class GatewayProperties {

    private String skipPrefix = "pub";

    private Cors cors = new Cors();

    public Cors getCors() {
        return cors;
    }

    public void setCors(Cors cors) {
        this.cors = cors;
    }

    public static class Cors {

        /**
         * 允许跨域访问的地址
         */
        private List<String> allowedOrigins = Collections.singletonList("*");
        /**
         * 允许的请求头
         */
        private List<String> allowedHeaders = Collections.singletonList("*");
        /**
         * 允许访问的方法
         */
        private List<String> allowedMethods = Collections.singletonList("*");

        public List<String> getAllowedOrigins() {
            return allowedOrigins;
        }

        public void setAllowedOrigins(List<String> allowedOrigins) {
            this.allowedOrigins = allowedOrigins;
        }

        public List<String> getAllowedHeaders() {
            return allowedHeaders;
        }

        public void setAllowedHeaders(List<String> allowedHeaders) {
            this.allowedHeaders = allowedHeaders;
        }

        public List<String> getAllowedMethods() {
            return allowedMethods;
        }

        public void setAllowedMethods(List<String> allowedMethods) {
            this.allowedMethods = allowedMethods;
        }
    }

}
