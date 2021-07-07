package cn.bootx.goodscenter.core.sku.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.goodscenter.dto.sku.GoodsSkuAttrDto;
import cn.bootx.starter.jpa.base.JpaTidEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import javax.persistence.Entity;
import javax.persistence.Table;

/**   
* SKU属性
* @author xxm  
* @date 2020/11/23 
*/
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Accessors(chain = true)
@Table(name = "gc_goods_sku_attr")
public class GoodsSkuAttr extends JpaTidEntity implements EntityBaseFunction<GoodsSkuAttrDto> {

    /** 商品 类目 id */
    private Long cid;

    /** 所属商品 id */
    private Long goodsId;

    /**  商品 SKU id */
    private Long skuId;

    /** 属性定义 id */
    private Long attrDefId;

    /** 属性值 */
    private String attrValue;

    /** 显示值 */
    private String attrValueDisplays;

    public static GoodsSkuAttr init(GoodsSkuAttrDto dto) {
        GoodsSkuAttr entity = new GoodsSkuAttr();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    @Override
    public GoodsSkuAttrDto toDto() {
        GoodsSkuAttrDto dto = new GoodsSkuAttrDto();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }
}
