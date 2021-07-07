package cn.bootx.goodscenter.dto.sku;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* 商品SKU
* @author xxm
* @date 2020/11/19
*/
@Data
@Accessors(chain = true)
@ApiModel(value = "商品SKU DTO")
public class GoodsSkuDto implements Serializable {
    private static final long serialVersionUID = 9214661437061650242L;

    @ApiModelProperty("主键")
    private Long id;
    @ApiModelProperty(value = "所属类目id", required = true)
    private Long cid;
    @ApiModelProperty(value = "所属商品id", required = true)
    private Long goodsId;
    @ApiModelProperty(value = "商户id", required = true)
    private Long shopId;
    @ApiModelProperty(value = "外部编码")
    private String code;
    @ApiModelProperty(value = "SKU名称", required = true)
    private String name;
    @ApiModelProperty(value = "SKU描述")
    private String description;
    @ApiModelProperty(value = "附加信息")
    private String addition;
    @ApiModelProperty(value = "存储事务id")
    private Long businessId;

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

    @ApiModelProperty("属性列表")
    private List<GoodsSkuAttrDto> attrList;

    @ApiModelProperty(value = "库存总量")
    private Integer capacity;
    @ApiModelProperty(value = "预占库存")
    private int locked;
    @ApiModelProperty(value = "已用库存")
    private int sold;
    @ApiModelProperty(value = "可用库存")
    private Integer available;

    @ApiModelProperty(value = "上架时间")
    private LocalDateTime saleOnTime;
    @ApiModelProperty(value = "下架时间")
    private LocalDateTime saleOffTime;

    private boolean unlimited;
    @ApiModelProperty(value = "售价")
    private BigDecimal price;
    @ApiModelProperty(value = "状态（0：准备，1：销售中，2：停售，3：失效，9：召回），默认0")
    private int state;
    @ApiModelProperty(value = "商品code")
    private String goodsCode;

    @ApiModelProperty(value = "价格表")
    private Map<String, Object> priceTable;

    @ApiModelProperty(value = "价格类型ID")
    private Long priceTypeId;

    public List<String> getAttrDefIdList() {
        if (attrDefIdList == null) {
            attrDefIdList = splitAppendList(attrDefIds);
        }
        return attrDefIdList;
    }

    public List<String> getAttrValueList() {
        if (attrValueList == null) {
            attrValueList = splitAppendList(attrValues);
        }
        return attrValueList;
    }


    public List<String> getAttrValueDisplayList() {
        if (attrValueDisplayList == null) {
            attrValueDisplayList = splitAppendList(attrValueDisplays);
        }
        return attrValueDisplayList;
    }

    /**
     * 将拼接的字符串拆分成数组形式
     */
    public static List<String> splitAppendList(String appendString) {
        if (StrUtil.isEmpty(appendString)) {
            return new ArrayList<>(0);
        }
        return StrUtil.split(appendString,',');
    }
}