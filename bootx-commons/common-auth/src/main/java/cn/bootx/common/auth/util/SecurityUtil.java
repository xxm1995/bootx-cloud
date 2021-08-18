package cn.bootx.common.auth.util;

import cn.bootx.common.core.code.CommonCode;
import cn.bootx.common.core.entity.UserDetail;
import cn.bootx.common.core.exception.BizException;
import cn.bootx.common.headerholder.HeaderHolder;
import cn.hutool.json.JSONUtil;
import com.auth0.jwt.JWT;
import lombok.experimental.UtilityClass;

import java.util.Optional;

/**   
* 认证工具类
* @author xxm  
* @date 2021/6/3 
*/
@UtilityClass
public class SecurityUtil {

    /**
     * 获取用户
     */
    public Optional<UserDetail> getCurrentUser(){
        return Optional.ofNullable(HeaderHolder.getJwtToken())
                .map(token ->{
                    String user = JWT.decode(token).getClaim(CommonCode.USER).asString();
                    return JSONUtil.toBean(user, UserDetail.class);
                });
    }

    /**
     * 获取用户id
     */
    public Long getUserId(){
        return getCurrentUser().map(UserDetail::getId).orElseThrow(() -> new BizException("未登录"));
    }

    /**
     * 获取用户
     */
    public UserDetail getUser(){
        return getCurrentUser().orElseThrow(() -> new BizException("未登录"));
    }

}
