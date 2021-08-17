package cn.bootx.iam.client;

import cn.bootx.iam.dto.auth.AuthInfoResult;

/**
*
* @author xxm
* @date 2021/7/14
*/
public interface LoginUserClient {

    /**
    * 获取登录信息
    */
    AuthInfoResult getUserInfo();
}
