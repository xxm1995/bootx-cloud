package cn.bootx.goodscenter.param.goods;

import cn.bootx.goodscenter.param.sku.CreateSkuParam;
import cn.bootx.goodscenter.param.sku.UpdateSkuParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
* @author xxm
* @date 2021/2/21
*/
@Data
@Accessors(chain = true)
@ApiModel("商品更新参数")
public class UpdateGoodsAndSkuParam implements Serializable {
    private static final long serialVersionUID = 7240095798154822572L;

    @ApiModelProperty("商品")
    private UpdateGoodsParam goodsParam;

    @ApiModelProperty("删除的sku")
    private List<Long> deleteIds;

    @ApiModelProperty("更新的sku")
    private List<UpdateSkuParam> updateSkuParams;

    @ApiModelProperty("新增的sku")
    private List<CreateSkuParam> createSkuParams;


}
