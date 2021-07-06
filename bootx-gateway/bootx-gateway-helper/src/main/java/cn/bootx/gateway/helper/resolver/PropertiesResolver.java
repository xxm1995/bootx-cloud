package cn.bootx.gateway.helper.resolver;


import cn.bootx.gateway.helper.domain.CommonRoute;

import java.util.Map;

/**
 * @author XCXCXCXCX
 * @date 2019/9/3
 * @project hzero-gateway-helper
 */
public interface PropertiesResolver<T> {

    Map<String, CommonRoute> resolveRoutes(T properties);

}
