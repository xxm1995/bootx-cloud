package cn.bootx.salescenter.exception.strategy;

import cn.bootx.common.web.exception.BizException;
import cn.bootx.salescenter.code.SalesCenterErrorCode;


public class StrategyAlreadyUsedException extends BizException {
    public StrategyAlreadyUsedException() {
        super(SalesCenterErrorCode.STRATEGY_ALREADY_USED, "已经使用的策略");
    }
}
