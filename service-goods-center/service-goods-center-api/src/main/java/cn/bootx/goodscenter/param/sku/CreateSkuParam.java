package cn.bootx.goodscenter.param.sku;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

/**
* @author xxm
* @date 2021/2/2
*/
@Data
@Accessors(chain = true)
@ApiModel("创建sku参数")
public class CreateSkuParam {

    @ApiModelProperty(value = "所属类目id", required = true)
    private Long cid;

    @ApiModelProperty(value = "所属商品id", required = true)
    private Long goodsId;

    @ApiModelProperty(value = "SKU名称", required = true)
    private String name;

    @ApiModelProperty(value = "外部编码")
    private String code;

    @ApiModelProperty(value = "SKU描述")
    private String description;

    @ApiModelProperty(value = "附加信息")
    private String addition;

    @ApiModelProperty("是否打包品")
    private boolean packing;

    @ApiModelProperty("是否无限库存")
    private boolean unlimited;

    @ApiModelProperty("初始库存")
    private Integer capacity;

    @ApiModelProperty(value = "售价")
    private BigDecimal price;

    @ApiModelProperty(value =
            "生成此商品 SKU 的所有 SKU 属性定义的 id 数组（前端展示使用，数组元素顺序与 attrValueList、attrValueDisplayList 保持一致）")
    private List<String> attrDefIdList;

    @ApiModelProperty(value = "生成此商品 SKU 的所有 SKU 属性值组（前端展示使用）")
    private List<String> attrValueList;

    @ApiModelProperty(value = "生成此商品 SKU 的所有 SKU 显示值数组（前端展示使用）")
    private List<String> attrValueDisplayList;

    @ApiModelProperty(value = "被打包品集合")
    private List<Long> packedSkuIds;

}
