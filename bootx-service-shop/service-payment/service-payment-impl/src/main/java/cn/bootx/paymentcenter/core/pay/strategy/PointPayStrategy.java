package cn.bootx.paymentcenter.core.pay.strategy;

import cn.bootx.paymentcenter.code.pay.PayStatusCode;
import cn.bootx.paymentcenter.code.pay.PayTypeCode;
import cn.bootx.paymentcenter.core.pay.exception.ExceptionInfo;
import cn.bootx.paymentcenter.core.pay.func.AbsPayStrategy;
import cn.bootx.paymentcenter.core.paymodel.point.dao.PointPaymentRepository;
import cn.bootx.paymentcenter.core.paymodel.point.manager.PointPaymentManager;
import cn.bootx.paymentcenter.core.paymodel.point.service.PointPayService;
import cn.bootx.paymentcenter.core.paymodel.point.service.PointService;
import cn.bootx.paymentcenter.exception.point.PointCountAbnormalException;
import cn.bootx.paymentcenter.exception.point.PointNotEnoughException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

/**   
* 积分支付策略
* @author xxm  
* @date 2021/3/18 
*/
@Scope(SCOPE_PROTOTYPE)
@Component
@RequiredArgsConstructor
public class PointPayStrategy extends AbsPayStrategy {

    private final PointService pointService;
    private final PointPayService pointPayService;
    private final PointPaymentManager pointPaymentManager;
    private final PointPaymentRepository pointPaymentRepository;

    /**
     * 获取支付类型
     */
    @Override
    public int getType() {
        return PayTypeCode.POINT;
    }

    /**
     * 支付前处理
     */
    @Override
    public void doBeforePayHandler() {
        if (getPayMode().getCount() <= 0) {
            throw new PointCountAbnormalException();
        }
        // 积分剩余检查
        int points = pointService.getAvailablePoint(this.getPayParam().getUserId());
        if (points < getPayMode().getCount()) {
            throw new PointNotEnoughException();
        }
    }

    /**
     * 支付
     */
    @Override
    public void doPayHandler() {
        // 消费积分
        pointPayService.consumePoint(this.getPayMode().getCount(), this.getPayment(), this.getPayParam().getUserId());
    }

    /**
     * 成功
     */
    @Override
    public void doSuccessHandler() {
        // 此处需要单独记录日志，目的是为了混合支付的情况下，积分支付完成后，如果后面的支付失败了，此时积分相关的操作是需要回滚的
        pointService.addPointLog(getPayMode().getCount(), getPayment(), getPayParam().getUserId());
    }

    /**
     * 错误处理
     */
    @Override
    public void doErrorHandler(ExceptionInfo exceptionInfo) {
        // 回复积分
        Optional.ofNullable(pointPaymentManager.findByPaymentId(getPayment().getId()))
                .ifPresent(pointPayments -> pointPayments.forEach(pointPayment -> {
                    if (pointPayment.getPayStatus() == PayStatusCode.TRADE_SUCCESS){
                        // 恢复积分-不记录日志
                        pointService.restorePointNotLog(pointPayment);
                    }
                    pointPayment.setPayStatus(exceptionInfo.getErrorCode());
                    pointPaymentRepository.save(pointPayment);
                }));
    }

    /**
     * 关闭本地支付记录
     */
    @Override
    public void doCloseHandler() {
        pointPayService.close(this.getPayment());
    }

}
