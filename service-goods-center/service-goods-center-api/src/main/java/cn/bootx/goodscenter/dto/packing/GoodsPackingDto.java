package cn.bootx.goodscenter.dto.packing;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
* @author xxm
* @date 2020/11/20
*/
@Data
@Accessors(chain = true)
@ApiModel(value = "商品打包关系DTO")
public class GoodsPackingDto implements Serializable {

    private static final long serialVersionUID = 7398760045437755829L;
    @ApiModelProperty(name = "goodsId", value = "打包品 id", required = true)
    private Long goodsId;
    @ApiModelProperty(name = "packedGoodsId", value = "被打包品 id", required = true)
    private Long packedGoodsId;
    @ApiModelProperty(name = "packedGoodsName", value = "被打包品名称（前端展示使用）")
    private String packedGoodsName;

}
