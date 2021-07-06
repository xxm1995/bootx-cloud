package cn.bootx.gateway.helper.util;


import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**   
* 请求计数实用程序
* @author xxm  
* @date 2021/6/1 
*/
public class RequestCountUtils {

    private static final String SPLIT = "::";

    private static final Pattern PATTERN = Pattern.compile("[0-9]+:[^:]+:[0-9]+.[0-9]+.[0-9]+.[0-9]+");

    /**
     * id  > monitorRuleId
     * uri > request uri
     * ip  > request origin ip
     */
    public static String generateKey(Long id, String uri, String ip) {
        return id + SPLIT + uri + SPLIT + ip;
    }

    public static boolean validateKey(String key) {
        return key != null && PATTERN.matcher(key).matches();
    }

    public static Long resolveId(String key) {
        return validateKey(key) ? Long.parseLong(doResolve(key, 0)) : null;
    }

    public static String resolveUri(String key) {
        return validateKey(key) ? doResolve(key, 1) : null;
    }

    public static String resolveIp(String key) {
        return validateKey(key) ? doResolve(key, 2) : null;
    }

    private static String doResolve(String key, int index) {
        String[] parts = key.split(SPLIT);
        if (parts.length >= index + 1) {
            return parts[index];
        }
        return StringUtils.EMPTY;
    }

}
