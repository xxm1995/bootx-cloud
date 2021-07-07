package cn.bootx.goodscenter.dto.packing;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**   
* 打包品SKU关联关系
* @author xxm  
* @date 2020/11/22 
*/
@Data
@Accessors(chain = true)
@ApiModel(value = "打包品SKU关联关系DTO")
public class GoodsSkuPackingDto implements Serializable {

    private static final long serialVersionUID = 5348220238706398806L;
    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty(name = "goodsId", value = "打包品 id", required = true)
    private Long goodsId;

    @ApiModelProperty(name = "goodsSkuId", value = "打包品SKU id", required = true)
    private Long goodsSkuId;

    @ApiModelProperty(name = "packedGoodsId", value = "被打包品 id", required = true)
    private Long packedGoodsId;

    @ApiModelProperty(name = "packedSkuId", value = "被打包品SKU id", required = true)
    private Long packedSkuId;

}
