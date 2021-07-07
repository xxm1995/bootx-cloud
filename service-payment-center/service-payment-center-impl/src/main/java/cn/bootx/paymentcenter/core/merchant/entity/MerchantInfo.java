package cn.bootx.paymentcenter.core.merchant.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.paymentcenter.core.merchant.convert.MerchantConvert;
import cn.bootx.paymentcenter.dto.merchant.MerchantInfoDto;
import cn.bootx.paymentcenter.param.merchant.MerchantInfoParam;
import cn.bootx.starter.jpa.base.JpaBaseEntity;
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
@Table(name = "pc_merchant_info")
public class MerchantInfo extends JpaBaseEntity implements EntityBaseFunction<MerchantInfoDto> {

    /** 商户号 */
    private String merchantNo;
    /** 商户名称 */
    private String merchantName;
    /** 联系人姓名 */
    private String contactName;
    /** 手机号 */
    private String phone;
    /** 邮箱 */
    private String email;
    /** 商户状态 */
    private Integer state;
    /** 商户备注 */
    private String remark;

    @Override
    public MerchantInfoDto toDto() {
        return MerchantConvert.CONVERT.convert(this);
    }

    public static MerchantInfo init(MerchantInfoDto in){
        return MerchantConvert.CONVERT.convert(in);
    }

    public static MerchantInfo init(MerchantInfoParam in){
        return MerchantConvert.CONVERT.convert(in);
    }
}
