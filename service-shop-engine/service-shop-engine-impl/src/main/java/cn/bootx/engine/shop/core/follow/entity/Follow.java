package cn.bootx.engine.shop.core.follow.entity;

import cn.bootx.starter.jpa.base.JpaBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**   
* 收藏
* @author xxm  
* @date 2021/2/18 
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@Table(name = "se_follow")
public class Follow extends JpaBaseEntity {

    /** 所属用户 */
    private Long userId;
    /** skuId */
    private Long skuId;
    /** goodsId */
    private Long goodsId;
    /** 类目 */
    private Long categoryId;
    /** 商店id */
    private Long shopId;

    /** sku/spu */
    private int type;

    /** 名称 */
    private String name;
    /** 价格 */
    private BigDecimal price;
}
