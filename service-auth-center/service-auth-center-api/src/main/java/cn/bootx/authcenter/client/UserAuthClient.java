package cn.bootx.authcenter.client;

import cn.bootx.authcenter.dto.*;
import cn.bootx.usercenter.dto.user.UserInfoDto;

/**   
* 用户认证接口
* @author xxm  
* @date 2020/5/6 20:01 
*/
public interface UserAuthClient {

    /**
     * 添加账户(需要有已存在的用户)
     */
    UserAuthDto addNew(UserAuthDto dto);

    /**
     * 新增用户账号和用户信息
     */
    UserAuthDto saveUserAuthAndInfo(UserAuthDto dto);

    /**
     * 账号登录验证
     */
    UserInfoDto loginByAllPattern(LoginParam loginParam);

    /**
     * AuthCode方式登陆
     */
    UserInfoDto loginByAuthCode(AuthCodeLoginParam param);

    /**
     * 手机验证码登陆
     */
    UserInfoDto loginByPhone(PhoneLoginParam param);

    /**
     * 邮箱登陆
     */
    UserInfoDto loginByEmail(MailLoginParam param);

    /**
     * 修改密码
     */
    void changePassword(Long userId, String passwordOld, String passwordNew);

    /**
     * 激活账号
     */
    UserAuthDto activeAccountById(Long id);

}
