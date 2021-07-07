package cn.bootx.paymentcenter.core.payconfig.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.paymentcenter.core.payconfig.convert.PayConfigConvert;
import cn.bootx.paymentcenter.dto.payconfig.PayChannelWayDto;
import cn.bootx.paymentcenter.param.payconfig.PayChannelWayParam;
import cn.bootx.starter.jpa.base.JpaBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
* 支付通道支持的支付方式
* @author xxm  
* @date 2021/6/30 
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@Table(name = "pc_pay_channel_way")
public class PayChannelWay extends JpaBaseEntity implements EntityBaseFunction<PayChannelWayDto> {
    /** 支付方式代码 */
    private String code;

    /** 支付方式名称 */
    private String name;

    /** 支付通道id */
    private Long channelId;

    /** 支付通道code */
    private String channelCode;
    /** 备注 */
    private String remark;

    @Override
    public PayChannelWayDto toDto() {
        return PayConfigConvert.CONVERT.convert(this);
    }

    public static PayChannelWay init(PayChannelWayDto in){
        return PayConfigConvert.CONVERT.convert(in);
    }

    public static PayChannelWay init(PayChannelWayParam in){
        return PayConfigConvert.CONVERT.convert(in);
    }
}
