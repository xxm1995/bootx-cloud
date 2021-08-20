package cn.bootx.payment.code.pay;

import cn.bootx.common.core.exception.BizException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 支付方式(通道/类型)枚举
 * @see PayChannelCode
 * @author xxm
 * @date 2021/6/30
 */
@AllArgsConstructor
@Getter
public enum PayTypeEnum {
    /** 支付宝 */
    ALI_PAY(PayChannelCode.ALI,"ALI_PAY"),
    /** 微信支付 */
    WECHAT_PAY(PayChannelCode.WECHAT,"WECHAT_PAY"),
    /** 现金 */
    CASH(PayChannelCode.CASH,"CASH"),
    /** 钱包 */
    WALLET(PayChannelCode.WALLET,"WALLET");

    /** 支付类型数字编码 */
    private final int no;
    /** 支付类型字符编码 */
    private final String code;

    /**
     * 列表
     */
    public static List<String> findPayTypes(){
        return Arrays.stream(PayChannelEnum.values())
                .map(PayChannelEnum::getCode)
                .collect(Collectors.toList());
    }

    /**
     * 根据code获取
     */
    public static PayChannelEnum findByCode(String code){
        return Arrays.stream(PayChannelEnum.values())
                .filter(e -> e.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new BizException("不存在的支付code"));
    }
    /**
     * 根据no获取
     */
    public static PayChannelEnum findByNo(int no){
        return Arrays.stream(PayChannelEnum.values())
                .filter(e -> e.getNo() == no)
                .findFirst()
                .orElseThrow(() -> new BizException("不存在的支付no"));
    }

    /**
     * 判断支付方式是否存在
     */
    public static boolean existsByCode(String code){
        return Arrays.stream(PayChannelEnum.values())
                .map(PayChannelEnum::getCode)
                .anyMatch(code::equals);
    }
}
