package cn.bootx.authcenter.utils;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;

/**
 * 密码工具类
 * @author network
 */
public class PasswordUtil {

    /**
     * md5加密 字符串做盐值
     */
    public static String md5(String originPwd, String salt) {
        originPwd = StrUtil.isEmpty(originPwd) ? "" : originPwd.trim();
        String passwordStr = originPwd + salt;
        return DigestUtils.md5DigestAsHex(passwordStr.getBytes());
    }

    /**
     * md5加密 时间做盐值
     */
    public static String md5(String originPwd, LocalDateTime salt) {
        return md5(originPwd, DateUtil.format(salt, DatePattern.NORM_DATETIME_PATTERN));
    }

    /**
     * md5加密
     */
    public static String md5(String originPwd) {
        originPwd = StrUtil.isEmpty(originPwd) ? "" : originPwd.trim();
        return DigestUtils.md5DigestAsHex(originPwd.getBytes());
    }
}
