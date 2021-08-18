package cn.bootx.notice.utils;


import cn.hutool.core.util.StrUtil;

/**
* @author xxm
* @date 2020/4/8 10:34
*/
public class SmsUtil {

    private SmsUtil() {
        // do nothing
    }

    public static String formatPhone(String phone) {
        // 格式化电话号码
        return StrUtil.isNotBlank(phone) ? phone.replaceAll("[^0-9]", "").replaceFirst("\\+1", "").replaceFirst("\\+86", "") : "";
    }
}
