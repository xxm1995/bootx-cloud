package cn.bootx.paymentcenter.param.merchant;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
*
* @author xxm
* @date 2021/6/29
*/
@Data
@Accessors(chain = true)
@ApiModel("商户应用参数")
public class MerchantAppParam implements Serializable {
    private static final long serialVersionUID = -853129554340871717L;

    private Long id;
    /** 应用名称 */
    private String appName;
    /** 商户id */
    private Long merchantId;
    /** 应用状态 */
    private Integer state;
    /** 备注 */
    private String remark;
}
