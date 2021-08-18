package cn.bootx.iam.client;

import cn.bootx.common.core.entity.UserDetail;

/**
* 会话管理
* @author xxm  
* @date 2021/8/18 
*/
public interface SessionClient {

    /**
     * 获取登录用户信息
     */
    UserDetail getUserDetail();

}
