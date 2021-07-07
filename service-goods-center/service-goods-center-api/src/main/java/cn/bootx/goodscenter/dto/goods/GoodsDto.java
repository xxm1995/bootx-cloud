package cn.bootx.goodscenter.dto.goods;

import cn.bootx.goodscenter.dto.sku.GoodsSkuDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
* @author xxm
* @date 2020/11/20
*/
@Data
@Accessors(chain = true)
@ApiModel(value = "商品DTO")
public class GoodsDto implements Serializable {
    private static final long serialVersionUID = 9191023721658520976L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "所属类目id", required = true)
    private Long cid;

    @ApiModelProperty(value = "所属类目名称（前端展示使用）")
    private String cname;

    @ApiModelProperty(value = "外部编码", required = true)
    private String code;

    @ApiModelProperty(value = "商品名称", required = true)
    private String name;

    @ApiModelProperty(value = "商品描述")
    private String description;

    /** banner图片 多个图片逗号分隔 */
    private String bannerUri;

    /** 商品介绍主图 多个图片逗号分隔 */
    private String mainUri;

    /** 显示上限价格 */
    private BigDecimal displayUpperPrice;

    /** 显示下限价格 */
    private BigDecimal displayLowerPrice;

    @ApiModelProperty(value = "是否打包品")
    private boolean packing;

    @ApiModelProperty("附加信息")
    private String addition;

    @ApiModelProperty("状态(1:可用，0:不可用)")
    private int state;

    @ApiModelProperty(value = "生成此商品 SKU 的所有 SKU 属性定义的 id 拼接串")
    private String attrDefIds;
    @ApiModelProperty(value =
            "生成此商品 SKU 的所有 SKU 属性定义的 id 数组（前端展示使用，数组元素顺序与 attrValueList、attrValueDisplayList 保持一致）")
    private List<String> attrDefIdList;
    @ApiModelProperty(value = "生成此商品 SKU 的所有 SKU 属性值的 拼接串")
    private String attrValues;
    @ApiModelProperty(value = "生成此商品 SKU 的所有 SKU 属性值的 id 数组（前端展示使用）")
    private List<String> attrValueList;
    @ApiModelProperty(value = "生成此商品 SKU 的所有 SKU 属性定义的显示值拼接串")
    private String attrValueDisplays;
    @ApiModelProperty(value = "生成此商品 SKU 的所有 SKU 属性定义的显示值数组（前端展示使用）")
    private List<String> attrValueDisplayList;

    @ApiModelProperty("商品属性列表")
    private List<GoodsAttrDto> attrList;

    @ApiModelProperty("价格表类型id")
    private Long priceTypeId;

    @ApiModelProperty("关联sku")
    private List<GoodsSkuDto> skus;

}