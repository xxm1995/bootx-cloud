package cn.bootx.ordercenter.core.swap.entity;

import cn.bootx.ordercenter.dto.swap.SwapOrderDetailDto;
import cn.bootx.common.jpa.base.JpaBaseEntity;
import org.springframework.beans.BeanUtils;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**   
* 换货单明细
* @author xxm  
* @date 2021/3/11 
*/
@Entity
@Table(name = "oc_swap_order_detail")
public class SwapOrderDetail extends JpaBaseEntity {

    private Long orderId;

    private Long skuId;

    private Long lastSkuId;

    private BigDecimal payAmount;

    private Long originDetailId;

    private Long lastDetailId;

    public SwapOrderDetail() {
    }

    public SwapOrderDetail(SwapOrderDetailDto param){
        BeanUtils.copyProperties(param, this);
    }

    public SwapOrderDetailDto toDto() {
        SwapOrderDetailDto dto = new SwapOrderDetailDto();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getLastSkuId() {
        return lastSkuId;
    }

    public void setLastSkuId(Long lastSkuId) {
        this.lastSkuId = lastSkuId;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public Long getOriginDetailId() {
        return originDetailId;
    }

    public void setOriginDetailId(Long originDetailId) {
        this.originDetailId = originDetailId;
    }

    public Long getLastDetailId() {
        return lastDetailId;
    }

    public void setLastDetailId(Long lastDetailId) {
        this.lastDetailId = lastDetailId;
    }
}
