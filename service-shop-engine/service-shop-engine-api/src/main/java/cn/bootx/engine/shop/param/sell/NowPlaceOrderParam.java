package cn.bootx.engine.shop.param.sell;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**   
*
* @author xxm  
* @date 2021/6/24 
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@ApiModel("立即下单")
public class NowPlaceOrderParam extends PlaceOrderParam implements Serializable {
    private static final long serialVersionUID = -7311542791255702438L;

    @ApiModelProperty("商品SkuId")
    private Long skuId;

    @ApiModelProperty("数量")
    private Integer count;

    @ApiModelProperty("用户")
    private Long userId;
}
