package cn.bootx.engine.shop.param.cart;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
* @author xxm
* @date 2021/2/17
*/
@Data
@Accessors(chain = true)
@ApiModel("购物车参数")
public class CartParam {

    private Long skuId;

    private Integer type;

}