package cn.bootx.goodscenter.dto.goods;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**   
*
* @author xxm  
* @date 2021/2/23 
*/
@Data
@Accessors(chain = true)
@ApiModel("商品销售属性")
public class GoodsSpecDto implements Serializable {
    private static final long serialVersionUID = -5420725057377793726L;

    /** 商品id */
    private Long goodsId;

    /** 类目规格属性定义(sku属性) */
    private Long attrDefId;

    /** 排序序号 */
    private Integer ordinal;

    /** 属性值 */
    private String attrValues;

    /** 属性显示值 */
    private String attrValueDisplays;
}
