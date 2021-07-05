package cn.bootx.starter.headerholder;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
*
* @author xxm
* @date 2021/4/20
*/
@ConfigurationProperties(prefix = "bootx.starter.header")
public class HeaderProperties {
    private List<String> headers = new ArrayList<>(0);

    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }
}
