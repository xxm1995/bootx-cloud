package cn.bootx.paymentcenter.core.merchant.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.paymentcenter.core.merchant.convert.MerchantConvert;
import cn.bootx.paymentcenter.dto.merchant.MerchantAppDto;
import cn.bootx.paymentcenter.param.merchant.MerchantAppParam;
import cn.bootx.common.jpa.base.JpaBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 商户
 * @author xxm
 * @date 2021/6/29
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@Table(name = "pc_merchant_app")
public class MerchantApp extends JpaBaseEntity implements EntityBaseFunction<MerchantAppDto> {

    /** 应用id */
    private String appId;
    /** 应用名称 */
    private String appName;
    /** 商户id */
    private Long merchantId;
    /** 商户号 */
    private String merchantNo;
    /** 应用状态 */
    private Integer state;
    /** 备注 */
    private String remark;

    @Override
    public MerchantAppDto toDto() {
        return MerchantConvert.CONVERT.convert(this);
    }

    public static MerchantApp init(MerchantAppDto in){
        return MerchantConvert.CONVERT.convert(in);
    }

    public static MerchantApp init(MerchantAppParam in){
        return MerchantConvert.CONVERT.convert(in);
    }
}
