package cn.bootx.paymentcenter.dto.merchant;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author xxm
* @date 2021/6/29
*/
@Data
@Accessors(chain = true)
@ApiModel("商户应用")
public class MerchantAppDto implements Serializable {
    private static final long serialVersionUID = 343634045781430145L;

    private Long id;
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
}
