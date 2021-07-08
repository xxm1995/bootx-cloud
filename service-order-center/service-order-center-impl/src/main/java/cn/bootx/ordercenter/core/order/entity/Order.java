package cn.bootx.ordercenter.core.order.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.ordercenter.code.OrderStatusCode;
import cn.bootx.ordercenter.dto.order.OrderDto;
import cn.bootx.starter.jpa.base.JpaBaseEntity;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**   
* 订单
* @author xxm  
* @date 2020/10/15 
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@Table(name = "oc_order")
public class Order extends JpaBaseEntity implements EntityBaseFunction<OrderDto> {

    /** 编码 */
    private String code;

    /** 渠道 */
    private Long channelId;

    /** 描述 */
    private String description;

    /** 购买用户ID */
    private Long userId;

    /**
     * 状态
     * @see OrderStatusCode
     */
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

    /** 附加参数 */
    private String addition;

    /** 订单明细 */
    @Transient
    private List<OrderDetail> orderDetails;

    public static Order init(OrderDto dto){
        Order entity = new Order();
        BeanUtil.copyProperties(dto,entity);
        return entity;
    }

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
