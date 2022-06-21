package cn.bootx.paymentcenter.dto.merchant;

import cn.bootx.common.web.rest.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author xxm
* @date 2021/6/29
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@ApiModel("商户参数")
public class MerchantInfoDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = -726224246784393308L;

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
}
