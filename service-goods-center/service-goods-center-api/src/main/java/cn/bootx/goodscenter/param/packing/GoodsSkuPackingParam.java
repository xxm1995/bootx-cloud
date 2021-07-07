package cn.bootx.goodscenter.param.packing;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
* @author xxm
* @date 2020/11/23
*/
@Data
@Accessors(chain = true)
@ApiModel("SKU打包品设置")
public class GoodsSkuPackingParam {

@ApiModelProperty("打包品 id")
    private Long goodsId;

    @ApiModelProperty("被打包品 id")
    private Long packedGoodsId;

    @ApiModelProperty("打包品SKU id")
    private Long skuId;

    @ApiModelProperty("被打包品SKU id")
    private List<Long> ids;
}
