package cn.bootx.goodscenter.param.sku;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
* @author xxm
* @date 2021/2/2
*/
@Data
@Accessors(chain = true)
@ApiModel("生成sku属性参数")
public class GenSkuParam implements Serializable {
    private static final long serialVersionUID = 8939703808257892072L;

    @ApiModelProperty(value = "商品 类目 id", required = true)
    private Long cid;

    @ApiModelProperty(value = "所属商品 id", required = true)
    private Long goodsId;

    @ApiModelProperty("商品 SKU 属性")
    private List<GenSkuAttrParam> skuAttrList;
}
