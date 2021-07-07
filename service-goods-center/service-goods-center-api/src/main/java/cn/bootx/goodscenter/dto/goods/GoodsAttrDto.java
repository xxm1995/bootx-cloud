package cn.bootx.goodscenter.dto.goods;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author xxm
* @date 2020/11/20
*/
@Data
@Accessors(chain = true)
@ApiModel(value = "商品属性DTO")
public class GoodsAttrDto implements Serializable {

    private static final long serialVersionUID = -1437003529670711609L;

    @ApiModelProperty("所属商品 id")
    private Long goodsId;

    @ApiModelProperty(value = "属性定义 id", required = true)
    private Long attrDefId;

    @ApiModelProperty("属性值")
    private String attrValue;

    @ApiModelProperty("显示用的属性值")
    private String attrValueDisplay;
}