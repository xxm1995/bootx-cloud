package cn.bootx.common.util;

import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;

/**
 * Base64 相关的工具类
 *
 * @author network
 */
public class Base64Util {

    /**
     * 编码
     *
     * @param str 要编码的字符串 不可为null
     * @return 编码后的字符串
     */
    public static String encode(String str) {
        return new String(Base64.encodeBase64(str.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * 编码
     *
     * @param binaryData 要编码的byte数组
     * @return 编码后的字符串
     */
    public static String encode(final byte[] binaryData) {
        return new String(Base64.encodeBase64(binaryData));
    }

    /**
     * 解码
     *
     * @param str 要解码的字符串 不可为null
     * @return 解码后的结果
     */
    public static String decode(String str) {
        return new String(Base64.decodeBase64(str.getBytes()), StandardCharsets.UTF_8);
    }

    /**
     * 解码
     *
     * @param binaryData 要解码的byte数组
     * @return 解码后的结果
     */
    public static String decode(byte[] binaryData) {
        return new String(Base64.decodeBase64(binaryData));
    }

    /**
     * 以URL安全的方式对字符串做Base64编码处理。
     *
     * @param str 要处理的字符串
     * @return base64编码后的字符串
     */
    public static String encodeURLSafe(String str) {
        return Base64.encodeBase64URLSafeString(str.getBytes());
    }

    /**
     * 以URL安全的方式对byte数组做Base64编码处理。
     *
     * @param binaryData 要处理的byte数组
     * @return base64编码后的字符串
     */
    public static String encodeURLSafe(byte[] binaryData) {
        return Base64.encodeBase64URLSafeString(binaryData);
    }


    /**
     * 以URL安全的方式对字符串做Base64解码处理。
     *
     * @param str 要解码的字符串
     * @return base64解码后的字符串
     */
    public static String decodeURLSafe(String str) {
        return Base64.encodeBase64URLSafeString(str.getBytes());
    }


    /**
     * 以URL安全的方式对byte数组做Base64解码处理。
     *
     * @param binaryData 要解码的byte数组
     * @return base64解码后的字符串
     */
    public static String decodeURLSafe(byte[] binaryData) {
        return Base64.encodeBase64URLSafeString(binaryData);
    }

}
