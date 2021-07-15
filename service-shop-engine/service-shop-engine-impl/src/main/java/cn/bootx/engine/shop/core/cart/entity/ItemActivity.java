package cn.bootx.engine.shop.core.cart.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**   
* 优惠活动
* @author xxm  
* @date 2021/2/15 
*/
@Data
@Accessors(chain = true)
public class ItemActivity {

    /** 优惠活动 */
    private Long actId;
    /** 显示名称 */
    private String name;
}
