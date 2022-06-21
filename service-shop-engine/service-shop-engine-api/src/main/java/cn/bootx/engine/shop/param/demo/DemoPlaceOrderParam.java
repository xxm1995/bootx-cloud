package cn.bootx.engine.shop.param.demo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author xxm
* @date 2021/7/23
*/
@Data
@Accessors(chain = true)
@ApiModel("demo下单参数")
public class DemoPlaceOrderParam implements Serializable {
    private static final long serialVersionUID = -1315486593900577267L;
    @ApiModelProperty("商品SkuId")
    private Long skuId;

    @ApiModelProperty("数量")
    private Integer count;
}
