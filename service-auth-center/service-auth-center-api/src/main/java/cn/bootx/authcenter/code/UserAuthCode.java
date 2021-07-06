package cn.bootx.authcenter.code;


/**   
* 用户认证常量code
* @author xxm  
* @date 2020/4/24 19:50 
*/
public interface UserAuthCode {

    /**
     * 账号密码登录
     */
    String LOGIN_TYPE_ACCOUNT = "AccountAndPassword";

    /**
     * authCode登录
     */
    String LOGIN_TYPE_AUTH_CODE = "authCode";

    /**
     * 手机验证码登录
     */
    String LOGIN_TYPE_PHONE = "Phone";

    /**
     * 第三方登录
     */
    String LOGIN_TYPE_THIRD_PARTY = "ThirdPartyLogin-";

}
