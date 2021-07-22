package cn.bootx.paymentcenter.core.payment.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.paymentcenter.code.pay.PayStatusCode;
import cn.bootx.paymentcenter.dto.pay.PayTypeInfo;
import cn.bootx.paymentcenter.dto.payment.PaymentDto;
import cn.bootx.starter.jpa.base.JpaBaseEntity;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
* 支付记录
* @author xxm
* @date 2020/12/8
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@Table(name = "pc_payment")
public class Payment extends JpaBaseEntity implements EntityBaseFunction<PaymentDto> {

    /** 用户ID */
    private Long userId;

    /** 标题 */
    private String title;

    /** 描述 */
    private String description;

    /** 商户号 */
    private String merchantNo;

    /** 商户应用appId */
    private String appId;

    /** 关联的业务id */
    private String businessId;

    /** 是否是异步支付 */
    private boolean syncPayMode;

    /** 异步支付方式 */
    private Integer syncPayTypeCode;

    /** 金额 */
    private BigDecimal amount;

    /** 错误码 */
    private String errorCode;

    /** 支付类型信息 */
    private String payTypeInfo;

    /**
     * 支付状态
     * @see PayStatusCode
     */
    private Integer payStatus;

    /** 支付时间 */
    private LocalDateTime payTime;

    @Override
    public PaymentDto toDto() {
        PaymentDto dto = new PaymentDto();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }

    /**
     * 获取支付类型
     */
    public List<PayTypeInfo> getPayTypeInfos(){
        if (StrUtil.isNotBlank(this.payTypeInfo)){
            JSONArray array = JSONUtil.parseArray(this.payTypeInfo);
            return JSONUtil.toList(array, PayTypeInfo.class);
        }
        return new ArrayList<>(0);
    }
}
