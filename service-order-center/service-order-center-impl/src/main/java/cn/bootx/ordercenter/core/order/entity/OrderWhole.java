package cn.bootx.ordercenter.core.order.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.ordercenter.dto.order.OrderAddressDto;
import cn.bootx.ordercenter.dto.order.OrderDto;
import cn.bootx.ordercenter.dto.order.OrderInvoiceDto;
import cn.bootx.common.jpa.base.JpaBaseEntity;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单完整信息
 * @see Order
 * @author xxm
 * @date 2021/1/31
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@Table(name = "oc_order")
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class OrderWhole extends JpaBaseEntity implements EntityBaseFunction<OrderDto> {

    /** 编码 */
    private String code;

    /** 渠道 */
    private Long channelId;

    /** 购买用户ID */
    private Long userId;

    /** 状态 */
    private int status;

    /** 支付时间 */
    private LocalDateTime payTime;

    /** 类型 */
    private Integer type;

    /** 总金额 */
    private BigDecimal totalAmount;

    /** 实付金额 */
    private BigDecimal payAmount;

    /** 所用优惠券 */
    private String couponIds;

    /** 业务id */
    private Long businessId;

    /** 订单明细 */
    @Transient
    private List<OrderDetail> orderDetails;

    /** 附加参数 */
    private String addition;

    /** 收货信息 */
    @Type(type = "json")
    private OrderAddressDto addressInfo;

    /** 发票信息 */
    @Type(type = "json")
    private OrderInvoiceDto invoiceInfo;


    @Override
    public OrderDto toDto() {
        OrderDto dto = new OrderDto();
        BeanUtil.copyProperties(this,dto);
        if (CollUtil.isNotEmpty(orderDetails)){
            dto.setDetails(orderDetails.stream().map(OrderDetail::toDto).collect(Collectors.toList()));
        }
        return dto;
    }
}
