package cn.bootx.paymentcenter.code.paymodel;

import cn.bootx.common.web.exception.BizException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
* 微信支付方式
* @author xxm
* @date 2021/7/2
*/
@Getter
@AllArgsConstructor
public enum WeChatPayTypeEnum {

    /** wap支付 */
    WAP(WeChatPayCode.WAP,"WAP"),
    /** 微信APP支付 */
    APP(WeChatPayCode.APP,"APP"),
    /** 微信公众号支付或者小程序支付 */
    JSAPI(WeChatPayCode.JSAPI,"JSAPI"),
    /** 微信扫码支付 */
    QRCODE(WeChatPayCode.QRCODE,"QRCODE"),
    /** 付款码支付 */
    BARCODE(WeChatPayCode.BARCODE,"BARCODE");

    /** 支付类型数字编码 */
    private final int no;
    /** 支付类型字符编码 */
    private final String code;

    /**
     * 根据code获取
     */
    public static WeChatPayTypeEnum findByCode(String code){
        return Arrays.stream(WeChatPayTypeEnum.values())
                .filter(e->e.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new BizException("微信支付方式不存在"));
    }
    /**
     * 根据code获取
     */
    public static WeChatPayTypeEnum findByNo(int no){
        return Arrays.stream(WeChatPayTypeEnum.values())
                .filter(e->e.getNo()== no)
                .findFirst()
                .orElseThrow(() -> new BizException("微信支付方式不存在"));

    }
}
