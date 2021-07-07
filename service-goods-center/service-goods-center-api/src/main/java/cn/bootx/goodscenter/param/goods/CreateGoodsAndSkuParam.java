package cn.bootx.goodscenter.param.goods;

import cn.bootx.goodscenter.param.sku.CreateSkuParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
* @author xxm
* @date 2021/2/2
*/
@Data
@Accessors(chain = true)
@ApiModel("创建商品及sku参数")
public class CreateGoodsAndSkuParam {

    @ApiModelProperty("商品")
    private CreateGoodsParam goodsParam;

    @ApiModelProperty("sku")
    private List<CreateSkuParam> skuParams;
}
