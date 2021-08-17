package cn.bootx.starter.auth.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotNull;

@Configuration
public class TokenJacksonUtilsConfiguration implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(@NotNull ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        ObjectMapper bean = configurableListableBeanFactory.getBean(ObjectMapper.class);
        ObjectMapper objectMapperCopy = bean.copy();
        //指定序列化输入的类型为非最终类型，除了少数“自然”类型（字符串、布尔值、整数、双精度），它们可以从 JSON 正确推断； 以及所有非最终类型的数组
        objectMapperCopy.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.WRAPPER_ARRAY)
                // null不序列化
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
        TokenJacksonUtils.setObjectMapper(objectMapperCopy);
    }
}