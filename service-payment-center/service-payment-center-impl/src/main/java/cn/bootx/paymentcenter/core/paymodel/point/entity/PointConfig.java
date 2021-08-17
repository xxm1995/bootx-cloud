package cn.bootx.paymentcenter.core.paymodel.point.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.paymentcenter.dto.point.PointConfigDto;
import cn.bootx.common.jpa.base.JpaBaseEntity;
import cn.hutool.core.bean.BeanUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author xxm
 * @date 2020/12/11
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@Table(name = "pc_point_config")
public class PointConfig extends JpaBaseEntity implements EntityBaseFunction<PointConfigDto> {

    /** 积分抵扣比例：单位积分=多少钱，默认0.1 */
    private BigDecimal consumeRate;

    /** 积分允许使用最低订单金额 默认0 */
    private BigDecimal minOrderAmount;

    /** 积分最低允许使用数量 */
    private Integer minPointCount;

    /** 积分最高允许使用数量：(最高金额) / 积分抵扣比例 ，默认99999 */
    private Integer maxPointCount;

    /** 允许部分积分使用 */
    private Boolean allowPartialPay;

    /** 0:未激活，1:激活，默认0 */
    private Integer status;

    @Override
    public PointConfigDto toDto() {
        PointConfigDto dto = new PointConfigDto();
        BeanUtil.copyProperties(this,dto);
        return dto;
    }
}
