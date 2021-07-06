package cn.bootx.usercenter.client;

import cn.bootx.usercenter.dto.user.UserInfoDto;
import cn.bootx.usercenter.param.user.UserInfoParam;

/**
 * 用户信息操作
 * @author xxm
 * @date 2020/5/8 13:56
 */
public interface UserInfoClient {

    UserInfoDto getById(Long id);

    UserInfoDto getByEmail(String email);

    UserInfoDto getByPhone(String phone);

    /**
     * 注册新用户 返回添加后用户信息, 已经存在则直接返回
     */
    UserInfoDto addUserInfo(UserInfoParam param);
}


