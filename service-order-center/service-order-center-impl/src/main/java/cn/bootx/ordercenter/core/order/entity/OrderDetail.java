package cn.bootx.ordercenter.core.order.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.ordercenter.dto.order.OrderDetailDto;
import cn.bootx.ordercenter.param.order.OrderDetailParam;
import cn.bootx.common.jpa.base.JpaBaseEntity;
import cn.hutool.core.bean.BeanUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.List;

/**
 * 订单明细
 * @author xxm
 * @date 2020/10/15
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@Table(name = "oc_order_detail")
public class OrderDetail extends JpaBaseEntity implements EntityBaseFunction<OrderDetailDto> {

    /** 订单id */
    private Long orderId;

    /** 店铺id */
    private Long shopId;

    /** 类目 */
    private Long categoryId;

    /** 商品id */
    private Long goodsId;

    /** 库存id */
    private Long skuId;

    /** 商品名称 */
    private String goodsTitle;

    /** 商品价格 */
    private BigDecimal goodsPrice;

    /** 数量 */
    private int num;

    /** 总价 */
    private BigDecimal totalAmount;

    /** 支付价 */
    private BigDecimal payAmount;

    /** 状态 */
    private Integer state;

    /** 策略映射 */
    @Transient
    private List<OrderStrategyMapping> mappings;

    public static OrderDetail init(OrderDetailParam param){
        OrderDetail entity = new OrderDetail();
        BeanUtil.copyProperties(param,entity);
        return entity;
    }

    @Override
    public OrderDetailDto toDto() {
        OrderDetailDto dto = new OrderDetailDto();
        BeanUtil.copyProperties(this,dto);
        return dto;
    }
}
