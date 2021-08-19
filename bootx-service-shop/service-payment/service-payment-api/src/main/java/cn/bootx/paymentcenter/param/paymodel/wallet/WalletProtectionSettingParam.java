package cn.bootx.paymentcenter.param.paymodel.wallet;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author xxm
* @date 2020/12/8
*/
@Data
@Accessors(chain = true)
@ApiModel("钱包安全设置的参数")
public class WalletProtectionSettingParam implements Serializable {

    private static final long serialVersionUID = -1490822953327049082L;
    @ApiModelProperty("钱包ID")
    private Long walletId;

    @ApiModelProperty("保护模式, 0禁用 1PINCode 2Finger/FaceId")
    private Integer protectionMode;

    @ApiModelProperty("Pin Code,如果保护模式为PinCode 则必填")
    private String pinCode;

}
