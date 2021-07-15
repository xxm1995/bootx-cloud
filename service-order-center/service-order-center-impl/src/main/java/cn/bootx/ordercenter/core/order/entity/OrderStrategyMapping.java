package cn.bootx.ordercenter.core.order.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.ordercenter.dto.order.OrderStrategyMappingDto;
import cn.bootx.starter.jpa.base.JpaBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * 订单策略映射
 * 用于保存策略计算过程中的结果，会保存当时计算的订单策略、订单类型（订单/明细）和价格变动数量
 * @author xxm
 * @date 2020/10/11
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@Table(name = "oc_order_strategy_mapping")
public class OrderStrategyMapping extends JpaBaseEntity implements EntityBaseFunction<OrderStrategyMappingDto> {

    /** 订单id */
    private Long orderId;

    /** 明细id */
    private Long orderDetailId;

    /** 策略类型  1 活动策略 2 优惠券策略*/
    private Integer strategyType;

    /** 优惠券ID */
    private Long couponId;

    /** 策略ID */
    private Long strategyId;

    /** 策略注册ID */
    private Long strategyRegisterId;

    /** 价格变动 */
    private BigDecimal priceChange;

    /** 描述 */
    @Column(name = "`desc`")
    private String desc;

    @Override
    public OrderStrategyMappingDto toDto() {
        OrderStrategyMappingDto dto = new OrderStrategyMappingDto();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }
}
