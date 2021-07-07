package cn.bootx.goodscenter.core.goods.entity;

import cn.bootx.starter.jpa.base.JpaTidEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
* 商品属性
* @author xxm
* @date 2021/2/2
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@Table(name = "gc_goods_attr")
public class GoodsAttr extends JpaTidEntity {

    /** 商品 类目 id */
    private Long cid;

    /** 所属商品 id */
    private Long goodsId;

    /** 属性定义 id */
    private Long attrDefId;

    /** 属性值 */
    private String attrValue;

    /** 显示值 */
    private String attrValueDisplays;
}
