package cn.bootx.common.web.util;


import cn.bootx.common.function.EntityBaseFunction;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* 分页工具类
* @author xxm
* @date 2020/8/27
*/
public class ResultConvertUtils {

    /**
     * list转换
     */
    public static <T> List<T> dtoListConvert(List<? extends EntityBaseFunction<T>>list){
        return list.stream().map(EntityBaseFunction::toDto).collect(Collectors.toList());
    }

    /**
     * 转换成Dto对象
     * @param object 原始对象
     * @param <T> 它的Dto对象
     * @return T
     */
    public static <T> T dtoConvert(EntityBaseFunction<T> object){
        return Optional.ofNullable(object).map(EntityBaseFunction::toDto).orElse(null);
    }
}
