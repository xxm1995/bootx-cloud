package cn.bootx.goodscenter.core.sku.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.common.web.code.CommonCode;
import cn.bootx.goodscenter.dto.sku.GoodsSkuDto;
import cn.bootx.starter.jpa.base.JpaBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    /** 所属商品 id */
    private Long goodsId;
    /** 商户id */
    private Long shopId;
    /** 外部编码 */
    private String outNo;
    /** 商品编码 */
    private String code;
    /** SKU 名称 */
    private String name;
    /** 描述 */
    private String description;
    /** 附加信息 */
    private String addition;
    /** 业务id */
    private Long businessId;
    /** 是否打包品 */
    private boolean packing;

    /** 生成此商品 SKU 的所有 SKU 属性定义的 id 拼接串 */
    private String attrDefIds;

    /** 生成此商品 SKU 的所有 SKU 属性值的 拼接串 */
    private String attrValues;

    /** 生成此 SKU 的所有 SKU 属性值显示值拼接串 */
    @Transient
    private String attrValueDisplays;

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

    /** 0下架(随商品状态) 1上架 2下架(手动操作) */
    private Integer saleState;

    /** 上架时间 */
    private LocalDateTime saleOnTime;
    /** 下架时间 */
    private LocalDateTime saleOffTime;

    /** 价格 */
    private BigDecimal price;

    /** 状态 */
    private int state;

    public static GoodsSku init(GoodsSkuDto dto) {
        GoodsSku entity = new GoodsSku();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    @Override
    public GoodsSkuDto toDto() {
        GoodsSkuDto dto = new GoodsSkuDto();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }

}
