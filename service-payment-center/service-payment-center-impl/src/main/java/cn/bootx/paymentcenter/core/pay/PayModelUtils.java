package cn.bootx.paymentcenter.core.pay;

import cn.bootx.common.core.exception.BizException;
import cn.bootx.paymentcenter.code.pay.PayTypeCode;
import cn.bootx.paymentcenter.param.pay.PayModeParam;
import cn.bootx.paymentcenter.param.pay.PayParam;
import lombok.experimental.UtilityClass;

import java.util.List;

/**
* 支付方式工具类
* @author xxm  
* @date 2021/4/21 
*/
@UtilityClass
public class PayModelUtils {

    /**
     * 判断是否有异步支付
     */
    public boolean isNotSync(List<PayModeParam> payModeParams){
        return payModeParams.stream()
                .map(PayModeParam::getType)
                .noneMatch(PayTypeCode.SYNC_TYPE::contains);
    }

    /**
     * 获取异步支付参数
     */
    public PayModeParam getSyncPayModel(PayParam payParam){
        return payParam.getPayModeList().stream()
                .filter(payMode -> PayTypeCode.SYNC_TYPE.contains(payMode.getType()))
                .findFirst()
                .orElseThrow(() -> new BizException("支付方式数据异常"));
    }

}
