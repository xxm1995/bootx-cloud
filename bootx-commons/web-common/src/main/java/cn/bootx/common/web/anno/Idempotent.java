package cn.bootx.common.web.anno;

import java.lang.annotation.*;

/**   
* 幂等性接口
* @author xxm  
* @date 2021/1/2 
*/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Idempotent {

    /**
     * 是否开启
     */
    boolean enable() default true;
}
