package cn.bootx.gateway.helper.domain;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 请求key
 * @author xxm
 * @date 2021/6/7
 */
@Data
@Accessors(chain = true)
public class RequestKey {
    /** 地址 */
    private String uri;
    /** 方法 */
    private String method;
    /** 服务名称 */
    private String serviceName;

}
