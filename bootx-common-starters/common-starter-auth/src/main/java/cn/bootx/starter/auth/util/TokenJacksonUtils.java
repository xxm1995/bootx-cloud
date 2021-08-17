package cn.bootx.starter.auth.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
* token用序列化工具类
* @author xxm
* @date 2021/8/17
*/
public class TokenJacksonUtils {
    private static ObjectMapper objectMapper;

    static void setObjectMapper(ObjectMapper objectMapper){
        TokenJacksonUtils.objectMapper = objectMapper;
    }

    /**
     * 对象序列化为json字符串,转换异常将被抛出
     */
    public static String toJson(Object o){
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("json序列化失败");
        }
    }

    /**
     *  JSON字符串转为实体类对象，转换异常将被抛出
     */
    public static <T> T toBean(String content, Class<T> valueType){
        try {
            return objectMapper.readValue(content,valueType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("json反序列化失败");
        }
    }
}
