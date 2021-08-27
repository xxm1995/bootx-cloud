package cn.bootx.starter.auth.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**   
* 认证参数
* @author xxm  
* @date 2021/7/30 
*/
@Getter
@Setter
@ConfigurationProperties(prefix = "bootx.starter.auth")
public class AuthProperties {

    /** 是否允许同一账号并发登录 (为true时允许一起登录, 为false时新登录挤掉旧登录) */
    private boolean concurrent = true;

    /** 每次清理过期数据间隔的时间 (单位: 秒) ，默认值30秒，设置为-1代表不启动定时清理 */
    private int dataRefreshPeriod = 30;

    /** 是否打开自动续签 */
    private Boolean autoRenew = true;

    /**
     * 不进行鉴权的路径
     */
    private List<String> ignoreUrls = new ArrayList<>();

    /**
     * 是否开启验证码验证
     */
    private boolean captcha;

    /**
     * 验证码有效时间(秒)
     */
    private int captchaTimeout = 60;

    /**
     * 盐值
     */
    private String salt = "salt";

    /**
     * 默认密码
     */
    private String defaultPassword = "123456";

    /**
     * openId类型参数名称
     */
    private String openIdTypeParameter = "openIdType";

}
