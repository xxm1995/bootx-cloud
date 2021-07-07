package cn.bootx.paymentcenter.core.payconfig.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.paymentcenter.code.pay.PayTypeEnum;
import cn.bootx.paymentcenter.core.payconfig.convert.PayConfigConvert;
import cn.bootx.paymentcenter.dto.payconfig.PayChannelDto;
import cn.bootx.paymentcenter.param.payconfig.PayChannelParam;
import cn.bootx.starter.jpa.base.JpaBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
* 支付通道
* @author xxm  
* @date 2021/6/30 
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@Table(name = "pc_pay_channel")
public class PayChannel extends JpaBaseEntity implements EntityBaseFunction<PayChannelDto> {

    /**
     * 通道代码(唯一)
     * @see PayTypeEnum
     */
    private String code;

    /** 名称 */
    private String name;

    /** 页面展示：卡片-图标 */
    private String icon;

    /** 页面展示：卡片-背景色 */
    private String bgColor;

    /** 备注 */
    private String remark;

    @Override
    public PayChannelDto toDto() {
        return PayConfigConvert.CONVERT.convert(this);
    }

    public static PayChannel init(PayChannelDto in){
        return PayConfigConvert.CONVERT.convert(in);
    }

    public static PayChannel init(PayChannelParam in){
        return PayConfigConvert.CONVERT.convert(in);
    }
}
