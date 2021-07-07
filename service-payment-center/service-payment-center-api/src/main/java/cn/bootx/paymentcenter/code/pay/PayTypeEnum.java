package cn.bootx.paymentcenter.code.pay;

import cn.bootx.common.web.exception.BizException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 支付方式(通道/类型)枚举
 * @see PayTypeCode
 * @author xxm
 * @date 2021/6/30
 */
@AllArgsConstructor
@Getter
public enum PayTypeEnum {
    /** 支付宝 */
    ALI_PAY(PayTypeCode.ALI_PAY,"ALI_PAY"),
    /** 微信支付 */
    WECHAT_PAY(PayTypeCode.WECHAT_PAY,"WECHAT_PAY"),
    /** 现金 */
    CASH(PayTypeCode.CASH,"CASH"),
    /** 钱包 */
    WALLET(PayTypeCode.WALLET,"WALLET"),
    POINT(PayTypeCode.POINT,"POINT");

    /** 支付类型数字编码 */
    private final int no;
    /** 支付类型字符编码 */
    private final String code;

    /**
     * 列表
     */
    public static List<String> findPayTypes(){
        return Arrays.stream(PayTypeEnum.values())
                .map(PayTypeEnum::getCode)
                .collect(Collectors.toList());
    }

    /**
     * 根据code获取
     */
    public static PayTypeEnum findByCode(String code){
        return Arrays.stream(PayTypeEnum.values())
                .filter(e -> e.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new BizException("不存在的支付code"));
    }
    /**
     * 根据no获取
     */
    public static PayTypeEnum findByNo(int no){
        return Arrays.stream(PayTypeEnum.values())
                .filter(e -> e.getNo() == no)
                .findFirst()
                .orElseThrow(() -> new BizException("不存在的支付no"));
    }

    /**
     * 判断支付方式是否存在
     */
    public static boolean existsByCode(String code){
        return Arrays.stream(PayTypeEnum.values())
                .map(PayTypeEnum::getCode)
                .anyMatch(code::equals);
    }
}
