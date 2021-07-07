package cn.bootx.goodscenter.core.packing.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.common.web.code.CommonCode;
import cn.bootx.goodscenter.dto.packing.GoodsSkuPackingDto;
import cn.bootx.starter.jpa.base.JpaBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.beans.BeanUtils;

import javax.persistence.Entity;
import javax.persistence.Table;

/**   
* 打包品SKU关联关系
* @author xxm  
* @date 2020/11/22 
*/
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Accessors(chain = true)
@Table(name = "gc_goods_sku_packing")
@SQLDelete(sql = "update gc_goods_sku_packing set deleted=" + CommonCode.DELETE_FLAG + " where id=? and version=? ")
@Where(clause = "deleted=" + CommonCode.NORMAL_FLAG)
public class GoodsSkuPack extends JpaBaseEntity implements EntityBaseFunction<GoodsSkuPackingDto> {

    /** 打包品 id */
    private Long goodsId;
    /** 打包品 SKU id */
    private Long goodsSkuId;
    /** 被打包品 id */
    private Long packedGoodsId;
    /** 被打包品SKU id */
    private Long packedSkuId;

    public static GoodsSkuPack init(GoodsSkuPackingDto dto) {
        GoodsSkuPack entity = new GoodsSkuPack();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    @Override
    public GoodsSkuPackingDto toDto() {
        GoodsSkuPackingDto dto = new GoodsSkuPackingDto();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }

}
