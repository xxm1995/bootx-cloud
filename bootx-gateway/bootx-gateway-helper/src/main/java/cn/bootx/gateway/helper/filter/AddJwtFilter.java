package cn.bootx.gateway.helper.filter;

import cn.bootx.gateway.helper.api.HelperFilter;
import cn.bootx.gateway.helper.context.RequestContext;
import cn.bootx.gateway.helper.domain.CheckState;
import cn.bootx.gateway.helper.properties.GatewayHelperProperties;
import cn.hutool.json.JSONException;
import cn.hutool.json.JSONUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
* 添加JWT token
* @author xxm
* @date 2021/6/1
*/
@Component
@RequiredArgsConstructor
public class AddJwtFilter implements HelperFilter {
    private final GatewayHelperProperties gatewayHelperProperties;
    @Override
    public int filterOrder() {
        return 50;
    }

    @Override
    public boolean run(RequestContext context) {
        try {
            String jwtKey = gatewayHelperProperties.getJwtKey();
            String jwt = JWT.create()
                    .withClaim("user", JSONUtil.toJsonStr(context.getCustomUserDetails()))
                    .sign(Algorithm.HMAC256(jwtKey));
            context.response.setJwt(jwt);
            return true;
        } catch (JSONException e) {
            context.response.setStatus(CheckState.EXCEPTION_GATEWAY_HELPER);
            context.response.setMessage("添加jwt_token出错");
            return false;
        }
    }
}
