package cn.bootx.starter.auth.session.domain;

import cn.bootx.common.core.entity.UserDetail;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
* 登录会话对象
* @author xxm
* @date 2021/8/17
*/
@Getter
@Setter
@Accessors(chain = true)
public class LoginSession implements Serializable {
    private static final long serialVersionUID = 5583979974284123518L;

    /** 此Session的创建时间 */
    private long createTime;

    /** 用户信息 */
    private UserDetail userDetail;

    /** 此Session的所有挂载数据 */
    private final Map<String, Object> dataMap = new ConcurrentHashMap<>();

    /**
     * 此Session绑定的token签名列表
     */
    private final List<TokenSign> tokenSignList = new ArrayList<>();

}
