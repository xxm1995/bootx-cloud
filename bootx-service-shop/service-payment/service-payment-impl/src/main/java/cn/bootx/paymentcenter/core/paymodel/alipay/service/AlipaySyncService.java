package cn.bootx.paymentcenter.core.paymodel.alipay.service;

import cn.bootx.paymentcenter.code.pay.PaySyncStatus;
import cn.bootx.paymentcenter.code.paymodel.AliPayCode;
import cn.bootx.paymentcenter.core.pay.dto.PaySyncResult;
import cn.bootx.paymentcenter.core.payment.entity.Payment;
import cn.bootx.paymentcenter.core.paymodel.alipay.dao.AlipayConfigManager;
import cn.bootx.paymentcenter.core.paymodel.alipay.entity.AlipayConfig;
import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.ijpay.alipay.AliPayApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

/**   
* 支付宝同步
* @author xxm  
* @date 2021/5/17 
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class AlipaySyncService {
    private final AlipayConfigManager alipayConfigManager;

    /**
     * 与支付宝网关同步状态
     * 1 远程支付成功
     * 2 交易创建，等待买家付款
     * 3 超时关闭
     * 4 查询不到
     * 5 查询失败
     */
    public PaySyncResult syncPayStatus(Payment payment, AlipayConfig alipayConfig){
        PaySyncResult paySyncResult = new PaySyncResult()
                .setPaySyncStatus(PaySyncStatus.FAIL);

        // 查询
        try {
            AlipayTradeQueryModel queryModel = new AlipayTradeQueryModel();
            queryModel.setOutTradeNo(String.valueOf(payment.getId()));
            AlipayTradeQueryResponse response = AliPayApi.tradeQueryToResponse(queryModel);
            String tradeStatus = response.getTradeStatus();

            // 支付完成
            if (Objects.equals(tradeStatus, AliPayCode.PAYMENT_TRADE_SUCCESS)
                    ||Objects.equals(tradeStatus, AliPayCode.PAYMENT_TRADE_FINISHED)){

                HashMap<String, String> map = new HashMap<>(1);
                map.put(AliPayCode.TRADE_NO,response.getTradeNo());
                return paySyncResult.setPaySyncStatus(PaySyncStatus.TRADE_SUCCESS)
                        .setMap(map);
            }
            // 待支付
            if (Objects.equals(tradeStatus, AliPayCode.PAYMENT_WAIT_BUYER_PAY)){
                return paySyncResult.setPaySyncStatus(PaySyncStatus.WAIT_BUYER_PAY);
            }
            // 已关闭
            if (Objects.equals(tradeStatus, AliPayCode.PAYMENT_TRADE_CLOSED)){
                return paySyncResult.setPaySyncStatus(PaySyncStatus.TRADE_CLOSED);
            }
            // 未找到
            if (Objects.equals(response.getSubCode(),AliPayCode.ACQ_TRADE_NOT_EXIST)){
                return paySyncResult.setPaySyncStatus(PaySyncStatus.NOT_FOUND);
            }

        } catch (AlipayApiException e) {
            log.error("查询订单失败:",e);
        }
        return paySyncResult;
    }
}
