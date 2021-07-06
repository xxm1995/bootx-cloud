package cn.bootx.gateway.helper.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.util.StringUtils;

import java.util.LinkedHashSet;
import java.util.Set;

/**   
* 路由信息
* @author xxm  
* @date 2021/6/7 
*/
@Data
@Accessors(chain = true)
public class CommonRoute {

    /**
     * 路由的 ID（默认与其映射键相同）。
     */
    private String id;

    /**
     * 路线的路径（模式），例如/foo/**
     */
    private String path;

    /**
     * 要映射到此路由的服务 ID（如果有）。您可以指定物理 URL 或服务，但不能同时指定两者。
     */
    private String serviceId;

    /**
     * 映射到路由的完整物理 URL。另一种方法是使用服务 ID 和服务发现来查找物理地址。
     */
    private String url;

    /**
     *标志以确定是否应在转发前去除此路由的前缀（路径，减去模式修补程序）。
     */
    private boolean stripPrefix = true;

    /**
     * 标志以指示此路由应该是可重试的（如果支持）。通常重试需要服务 ID 和功能区。
     */
    private Boolean retryable;

    /**
     * 未传递给下游请求的敏感标头列表。默认为通常包含用户凭据的“安全”标头集。如果下游服务与代理属于同一系统，
     * 则可以从列表中删除它们，因此它们共享身份验证数据。如果在您自己的域之外使用物理 URL，那么泄露用户凭据通常是个坏主意。
     */
    private Set<String> sensitiveHeaders = new LinkedHashSet<>();

    private boolean customSensitiveHeaders = false;

    public CommonRoute() {
    }

    public CommonRoute(String id, String path, String serviceId, String url,
                       boolean stripPrefix, Boolean retryable, Set<String> sensitiveHeaders) {
        this.id = id;
        this.path = path;
        this.serviceId = serviceId;
        this.url = url;
        this.stripPrefix = stripPrefix;
        this.retryable = retryable;
        this.sensitiveHeaders = sensitiveHeaders;
        this.customSensitiveHeaders = sensitiveHeaders != null;
    }

    public CommonRoute(String text) {
        String location = null;
        String path = text;
        if (text.contains("=")) {
            String[] values = StringUtils
                    .trimArrayElements(StringUtils.split(text, "="));
            location = values[1];
            path = values[0];
        }
        this.id = extractId(path);
        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        setLocation(location);
        this.path = path;
    }

    public CommonRoute(String path, String location) {
        this.id = extractId(path);
        this.path = path;
        setLocation(location);
    }

    public String getLocation() {
        if (StringUtils.hasText(this.url)) {
            return this.url;
        }
        return this.serviceId;
    }

    public void setLocation(String location) {
        if (location != null
                && (location.startsWith("http:") || location.startsWith("https:"))) {
            this.url = location;
        } else {
            this.serviceId = location;
        }
    }

    private String extractId(String path) {
        path = path.startsWith("/") ? path.substring(1) : path;
        path = path.replace("/*", "").replace("*", "");
        return path;
    }

    public void setSensitiveHeaders(Set<String> headers) {
        this.customSensitiveHeaders = true;
        this.sensitiveHeaders = new LinkedHashSet<>(headers);
    }
}
