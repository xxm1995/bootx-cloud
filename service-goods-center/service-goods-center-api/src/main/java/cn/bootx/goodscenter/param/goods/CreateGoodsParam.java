package cn.bootx.goodscenter.param.goods;

import cn.bootx.goodscenter.dto.goods.GoodsAttrDto;
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
@ApiModel("商品创建参数")
public class CreateGoodsParam {

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

    @ApiModelProperty(value = "是否打包品")
    private boolean packing;

    @ApiModelProperty("商品属性列表")
    private List<GoodsAttrDto> attrList;

    @ApiModelProperty("附加信息")
    private String addition;

    @ApiModelProperty("价格表类型id")
    private Long priceTypeId;

}
