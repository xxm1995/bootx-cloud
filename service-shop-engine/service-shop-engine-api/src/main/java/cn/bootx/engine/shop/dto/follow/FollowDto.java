package cn.bootx.engine.shop.dto.follow;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
* @author xxm
* @date 2021/2/18
*/
@Data
@Accessors(chain = true)
@ApiModel("收藏关注")
public class FollowDto implements Serializable {

    private Long id;

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
