package cn.bootx.salescenter.exception.strategy;

import cn.bootx.common.web.exception.BizException;
import cn.bootx.salescenter.code.SalesCenterErrorCode;

/**
 *
 * @author zhangguoquan
 * @date 18/4/8
 */
public class StrategyNotExistException extends BizException {
    public StrategyNotExistException() {
        super(SalesCenterErrorCode.STRATEGY_NOT_EXIST, "策略不存在");
    }
}
