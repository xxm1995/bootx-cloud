package cn.bootx.goodscenter.core.goods.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.goodscenter.dto.goods.GoodsSpecDto;
import cn.bootx.starter.jpa.base.JpaTidEntity;
import cn.hutool.core.bean.BeanUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**   
* 商品销售规格
* @author xxm  
* @date 2021/2/23 
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class GoodsSpec extends JpaTidEntity implements EntityBaseFunction<GoodsSpecDto> {

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

    @Override
    public GoodsSpecDto toDto() {
        GoodsSpecDto dto = new GoodsSpecDto();
        BeanUtil.copyProperties(this,dto);
        return dto;
    }
}
