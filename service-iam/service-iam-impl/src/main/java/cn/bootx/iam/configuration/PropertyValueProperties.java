package cn.bootx.iam.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Objects;

/**
 * 配置中心的属性值容器
 * 用户中心 需要用到的所有在配置中心进行配置的属性值都放在这里。
 *
 * @author xxm
 */
@ConfigurationProperties(prefix="bootx.property")
public class PropertyValueProperties {

    private static final Long DEFAULT_SESSION_TIMEOUT = 30 * 60 * 1000L;
    private static final Long DEFAULT_CLIENT_TIMEOUT = 24 * 60 * 60 * 1000L;

    private String passwordSalt;

    private Long sessionTimeout;
    /**
     * 获取 密码 加密使用的 salt
     */
    public String getPasswordSalt() {
        return passwordSalt;
    }

    /**
     * 获取session 超时的时长
     *
     * @return 时长
     */
    public Long getSessionTimeout() {
        return Objects.isNull(sessionTimeout) ? DEFAULT_SESSION_TIMEOUT : sessionTimeout;
    }

    public Long getClientTimeout() {
        return DEFAULT_CLIENT_TIMEOUT;
    }

    public PropertyValueProperties setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
        return this;
    }

    public PropertyValueProperties setSessionTimeout(Long sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
        return this;
    }

}
