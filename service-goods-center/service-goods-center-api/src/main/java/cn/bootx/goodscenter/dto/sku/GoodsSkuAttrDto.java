package cn.bootx.goodscenter.dto.sku;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author xxm
* @date 2020/11/21
*/
@Data
@Accessors(chain = true)
@ApiModel(value = "商品 SKU 属性DTO")
public class GoodsSkuAttrDto implements Serializable {

    private static final long serialVersionUID = 8050952891407800854L;

    @ApiModelProperty(value = "属性定义 id", required = true)
    private Long attrDefId;
    @ApiModelProperty(value = "商品 SKU id")
    private Long skuId;
    @ApiModelProperty(value = "属性值")
    private String attrValue;
    @ApiModelProperty(value = "显示用的属性值")
    private String attrValueDisplays;
}
