package cn.bootx.paymentcenter.dto.merchant;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**   
* @author xxm
* @date 2021/6/30 
*/
@Data
@Accessors(chain = true)
@ApiModel("应用支付配置")
public class AppChannelDto implements Serializable {
    private static final long serialVersionUID = -117478352296451474L;

    /** 未开通/启用/停用 */
    private int state;

}
