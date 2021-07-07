package cn.bootx.goodscenter.param.packing;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
* @author xxm
* @date 2020/11/23
*/
@Data
@Accessors(chain = true)
@ApiModel(value = "商品打包品设置")
public class GoodsPackingParam {

    @ApiModelProperty(name = "goodsId", value = "打包品 id", required = true)
    @NotNull(message = "goodsId不能为null")
    private Long goodsId;
    @ApiModelProperty(name = "cid", value = "商品类目 id", required = true)
    @NotNull(message = "cid不能为null")
    private Long cid;
    @ApiModelProperty(name = "ids", value = "被打包品 id 列表", required = true)
    @NotNull(message = "ids不能为null")
    private List<Long> ids;

}
