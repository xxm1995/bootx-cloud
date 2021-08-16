package cn.bootx.salescenter.exception.strategy;

import cn.bootx.common.core.exception.BizException;
import cn.bootx.salescenter.code.SalesCenterErrorCode;

/**
*
* @author xxm
* @date 2020/10/11
*/
public class StrategyRegisterAlreadyExistException extends BizException {
    public StrategyRegisterAlreadyExistException() {
        super(SalesCenterErrorCode.STRATEGY_REGISTER_ALREADY_EXIST, "策略已经注册");
    }
}
