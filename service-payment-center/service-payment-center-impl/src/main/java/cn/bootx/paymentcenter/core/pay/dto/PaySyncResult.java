package cn.bootx.paymentcenter.core.pay.dto;

import cn.bootx.paymentcenter.code.pay.PaySyncStatus;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

/**   
* 支付网关通知状态对象
* @author xxm
* @date 2021/4/21 
*/
@Data
@Accessors(chain = true)
public class PaySyncResult {
    /**
     * 支付网关同步状态
     * @see PaySyncStatus
     */
    private int paySyncStatus = -1;

    /** 网关返回参数 */
    private Map<String,String> map;
}
