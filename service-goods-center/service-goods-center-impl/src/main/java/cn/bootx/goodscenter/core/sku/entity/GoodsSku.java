package cn.bootx.goodscenter.core.sku.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.common.core.code.CommonCode;
import cn.bootx.goodscenter.core.sku.convert.SkuConvert;
import cn.bootx.goodscenter.dto.sku.GoodsSkuDto;
import cn.bootx.goodscenter.param.sku.SkuParam;
import cn.bootx.starter.jpa.base.JpaBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * 商品 SKU
 * @author xxm
 * @date 2020/11/19
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Accessors(chain = true)
@Table(name = "gc_goods_sku")
@SQLDelete(sql = "update gc_goods_sku set deleted=" + CommonCode.DELETE_FLAG + " where id=? and version=? ")
@Where(clause = "deleted=" + CommonCode.NORMAL_FLAG)
public class GoodsSku extends JpaBaseEntity implements EntityBaseFunction<GoodsSkuDto> {

    /**  所属类目id */
    private Long cid;
    /** 商品 id */
    private Long goodsId;
    /** 外部编码 */
    private String outNo;
    /** SKU 名称 */
    private String name;
    /** 描述 */
    private String description;
    /** 附加信息 */
    private String addition;

    /** 是否无限库存 */
    @Column(name = "is_unlimited")
    private boolean unlimited;

    /** 初始库存 */
    private Integer capacity;
    /** 预占库存 */
    private int locked;
    /** 已用库存 */
    private int sold;
    /** 可用库存 */
    private Integer available;

    /** 价格 */
    private BigDecimal price;

    /** 状态 */
    private int state;

    public static GoodsSku init(GoodsSkuDto in) {
        return SkuConvert.CONVERT.convert(in);
    }

    public static GoodsSku init(SkuParam in) {
        return SkuConvert.CONVERT.convert(in);
    }

    @Override
    public GoodsSkuDto toDto() {
        return SkuConvert.CONVERT.convert(this);
    }

}
