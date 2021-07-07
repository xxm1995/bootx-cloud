package cn.bootx.paymentcenter.dto.payconfig;

import lombok.Data;
import lombok.experimental.Accessors;

/**
* 支付通道支持的支付方式
* @author xxm  
* @date 2021/6/30 
*/
@Data
@Accessors(chain = true)
public class PayChannelWayDto {
    /** 支付方式代码 */
    private String code;

    /** 支付方式名称 */
    private String name;

    /** 通道id */
    private Long channelId;

    /** 通道code */
    private String channelCode;

    /** 备注 */
    private String remark;

}
