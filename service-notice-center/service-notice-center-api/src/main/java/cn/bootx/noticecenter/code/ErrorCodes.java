package cn.bootx.noticecenter.code;

public final class ErrorCodes {
    /**
     * 邮箱配置编号已存在
     */
    public static final int MAIL_CONFIG_CODE_ALREADY_EXISTED = 24010;

    /**
     * 邮箱配置不存在
     */
    public static final int MAIL_CONFIG_NOT_EXIST = 24011;

    /**
     * 默认邮箱配置已存在
     */
    public static final int DEFAULT_MAIL_CONFIG_ALREADY_EXISTED = 24012;

    /**
     * 短信配置编号已存在
     */
    public static final int SMS_CONFIG_CODE_ALREADY_EXISTED = 24020;

    /**
     * 短信配置不存在
     */
    public static final int SMS_CONFIG_NOT_EXIST = 24021;

    /**
     * 默认短信配置已存在
     */
    public static final int DEFAULT_SMS_CONFIG_ALREADY_EXISTED = 24022;

    /**
     * Bandwidth 短信发送失败
     */
    public static final int BANDWIDTH_SMS_SEND_ERROR = 24023;

    /**
     * WeChat配置编号已存在
     */
    public static final int WECHAT_CONFIG_CODE_ALREADY_EXISTED = 24030;

    /**
     * WeChat配置不存在
     */
    public static final int WECHAT_CONFIG_NOT_EXIST = 24031;

    /**
     * 邮箱配置不存在
     */
    public static final int MAIL_TEMPLATE_NOT_EXIST = 24041;

    private ErrorCodes() {
        // do nothing
    }
}
