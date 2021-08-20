package cn.bootx.payment.param.paymodel.alipay;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author xxm
* @date 2021/7/22
*/
@Data
@Accessors(chain = true)
@ApiModel("支付宝配置搜索参数")
public class AlipayConfigQuery implements Serializable {
    private static final long serialVersionUID = -173325268481050362L;

    /** 名称 */
    private String name;

    /** 状态 */
    private Integer state;

    /** 支付宝商户appId */
    private String appId;
}
