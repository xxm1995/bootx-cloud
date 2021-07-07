package cn.bootx.goodscenter.param.sku;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
* @author xxm
* @date 2021/2/5
*/
@Data
@Accessors(chain = true)
@ApiModel("生成sku属性定义")
public class GenSkuAttrParam {

    @ApiModelProperty(value = "属性定义 id", required = true)
    private Long attrDefId;

    @ApiModelProperty(value = "属性值列表,size需要与 attrValueListDisplay 一致", required = true)
    private List<String> attrValues;

    @ApiModelProperty(value = "显示用属性值列表,size需要与 attrValueList 一致", required = true)
    private List<String> attrValueDisplays;

}
