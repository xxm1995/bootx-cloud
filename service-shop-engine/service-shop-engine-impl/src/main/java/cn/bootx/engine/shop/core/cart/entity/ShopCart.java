package cn.bootx.engine.shop.core.cart.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

/**   
* 购物车数据
* @author xxm  
* @date 2021/1/4 
*/
@Data
@Accessors(chain = true)
public class ShopCart {

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("渠道")
    private Long channelId;

    @ApiModelProperty("明细信息")
    private LinkedHashMap<Long,ShopCartItem> cartItems;

    @ApiModelProperty("总金额")
    private BigDecimal totalAmount;

    @ApiModelProperty( "待支付总金额")
    private BigDecimal payAmount;
}
