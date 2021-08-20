package cn.bootx.payment.core.pay.exception;

/**
 * 支付异常信息
 * @author xxm
 * @date 2020/12/9
 */
public interface BaseException {

    /**
     * 获取错误信息
     */
    ExceptionInfo getExceptionInfo();

}
