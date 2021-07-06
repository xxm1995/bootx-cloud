package cn.bootx.gateway.route.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**   
* 服务路由配置
* @author xxm  
* @date 2021/6/4 
*/
@Data
@Accessors(chain = true)
public class ServiceRoute{

    public ServiceRoute() {
    }

    public ServiceRoute(@NotNull String serviceCode, @NotBlank String name, @NotBlank String path) {
        this.serviceCode = serviceCode;
        this.name = name;
        this.path = path;
    }

    private Long serviceRouteId;
    private Long serviceId;
    private String serviceCode;
    private String name;
    private String path;
    private String url;
    private Integer stripPrefix;
    private String sensitiveHeaders;
    private String extendConfigMap;
}
