package cn.bootx.paymentcenter.core.merchant.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.paymentcenter.dto.merchant.AppChannelDto;
import cn.bootx.starter.jpa.base.JpaBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
* 商户支付通道配置
* @author xxm
* @date 2021/6/30
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@Table(name = "pc_app_channel")
public class AppChannel extends JpaBaseEntity implements EntityBaseFunction<AppChannelDto> {

    /** 应用AppId */
    private String appId;

    /** 支付渠道code */
    private int no;

    /** 支付渠道code */
    private String code;

    /** 状态 */
    private Integer state;

    @Override
    public AppChannelDto toDto() {
        return null;
    }
}
