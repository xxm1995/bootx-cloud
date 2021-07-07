package cn.bootx.paymentcenter.core.pay.strategy;

import cn.bootx.common.util.BigDecimalUtil;
import cn.bootx.paymentcenter.code.pay.PayTypeCode;
import cn.bootx.paymentcenter.core.pay.exception.ExceptionInfo;
import cn.bootx.paymentcenter.core.pay.func.AbsPayStrategy;
import cn.bootx.paymentcenter.core.paymodel.cash.service.CashService;
import cn.bootx.paymentcenter.exception.payment.PayAmountAbnormalException;
import cn.bootx.paymentcenter.param.pay.PayModeParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

/**   
* 现金支付
* @author xxm  
* @date 2021/6/23 
*/
@Slf4j
@Scope(SCOPE_PROTOTYPE)
@Service
@RequiredArgsConstructor
public class CashPayStrategy extends AbsPayStrategy {
    private final CashService cashService;

    /**
     * 现金支付
     */
    @Override
    public int getType() {
        return PayTypeCode.CASH;
    }

    @Override
    public void doBeforePayHandler() {
        // 检查金额
        PayModeParam payMode = this.getPayMode();
        if (BigDecimalUtil.compareTo(payMode.getAmount(), BigDecimal.ZERO) < 1){
            throw new PayAmountAbnormalException();
        }
    }
    /**
     * 支付操作
     */
    @Override
    public void doPayHandler() {
        cashService.pay(this.getPayMode(),
                this.getPayment(),
                this.getPayParam());
    }

    /**
     * 支付失败的处理方式
     */
    @Override
    public void doErrorHandler(ExceptionInfo exceptionInfo) {
        this.doCloseHandler();
    }

    /**
     * 关闭本地交易单
     */
    @Override
    public void doCloseHandler() {
        cashService.close(this.getPayment().getId());
    }
}
