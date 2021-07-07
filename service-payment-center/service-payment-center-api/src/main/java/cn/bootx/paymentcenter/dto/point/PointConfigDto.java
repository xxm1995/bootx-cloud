package cn.bootx.paymentcenter.dto.point;

import cn.bootx.common.web.rest.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
* @author xxm
* @date 2021/2/25
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@ApiModel("积分配置")
public class PointConfigDto extends BaseDto {

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

    /** 0:未激活，1:激活，默认0" */
    private Integer status;
}
