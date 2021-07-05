package cn.bootx.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**   
* jackson常用工具类封装
* @author xxm  
* @date 2020/11/29 
*/
public class JacksonUtils {
    private static final ObjectMapper JACK = new ObjectMapper();

    /**
     * 对象序列化为json字符串,转换异常将被抛出
     */
    public static String toJson(Object o){
        try {
            return JACK.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("json序列化失败");
        }
    }

    /**
     *  JSON字符串转为实体类对象，转换异常将被抛出
     */
    public static <T> T toBean(String content, Class<T> valueType){
        try {
            return JACK.readValue(content,valueType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("json反序列化失败");
        }
    }
}
