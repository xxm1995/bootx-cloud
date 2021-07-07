package cn.bootx.goodscenter.dto.category;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* 类目属性定义 Dto
* @author xxm
* @date 2020/11/21
*/
@Data
@Accessors(chain = true)
@ApiModel(value = "类目属性定义DTO")
public class CategoryAttrDefDto implements Serializable {
    private static final long serialVersionUID = -3834910289320099208L;

    @ApiModelProperty("主键")
    private Long id;
    @ApiModelProperty("类目id")
    private Long cid;
    @ApiModelProperty("类目名称（前端展示使用）")
    private String cname;
    @ApiModelProperty("属性名称")
    private String name;
    @ApiModelProperty("属性类型(类型之枚举:1;类型之整数:2;类型之小数:3;类型之日期:5;类型之时间:" +
            "6;类型之字符串:7;类型之数据字典:101;类型之城市:102;类型之JSON:110;)")
    private int type;

    @ApiModelProperty("目标类型 sku/spu")
    private int targetType;

    @ApiModelProperty("是否显示属性")
    private boolean isDisplay;
    @ApiModelProperty("是否搜索属性")
    private boolean isSearch;
    @ApiModelProperty("是否必须")
    private boolean isRequired;

    @ApiModelProperty("数据字典id")
    private Long dictId;
    @ApiModelProperty("是否多选, 默认否")
    private boolean isMultiple;
    @ApiModelProperty("序号，默认0")
    private int ordinal;
    @ApiModelProperty("状态(可用:1,不可用:0)，默认1")
    private int state;
    @ApiModelProperty("字段名称")
    private String fieldName;
    @ApiModelProperty("字段长度")
    private Integer fieldLength;
    @ApiModelProperty("小数点长度")
    private Integer fieldPointLength;
    @ApiModelProperty("字段类型(String，Date，Integer，Double，byte[]，Boolean，BigDecimal，Long, Text)")
    private String fieldType;
    @ApiModelProperty("描述")
    private String description;
    @ApiModelProperty("字段默认值")
    private String fieldDefault;
    @ApiModelProperty("字段查询时的比较方式(equals,like,greatThan,LessThan)")
    private String queryCompareType;
    @ApiModelProperty("主键")
    private Boolean isKey;

}