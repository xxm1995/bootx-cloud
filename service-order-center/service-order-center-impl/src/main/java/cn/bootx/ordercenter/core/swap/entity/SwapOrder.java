package cn.bootx.ordercenter.core.swap.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.ordercenter.core.order.entity.Order;
import cn.bootx.ordercenter.dto.swap.SwapOrderDto;
import cn.bootx.starter.jpa.base.JpaBaseEntity;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**   
* 换货单
* @author xxm  
* @date 2021/3/11 
*/
@Entity
@Table(name = "oc_swap_order")
public class SwapOrder extends JpaBaseEntity implements EntityBaseFunction<SwapOrderDto> {

    private Long channelId;

    private BigDecimal payAmount;

    private Long originOrderId;

    private Long lastOrderId;

    private String deviceId;

    private Date payTime;

    private int state;

    private Long businessId;

    @OneToMany
    @JoinColumn(name = "orderId", insertable = false, updatable = false)
    private List<SwapOrderDetail> swapOrderDetails;

    @ManyToOne
    @JoinColumn(name = "originOrderId", insertable = false, updatable = false)
    private Order originOrder;


    public static SwapOrder init(SwapOrderDto dto){
        SwapOrder entity = new SwapOrder();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    @Override
    public SwapOrderDto toDto() {
        SwapOrderDto dto = new SwapOrderDto();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }
}
