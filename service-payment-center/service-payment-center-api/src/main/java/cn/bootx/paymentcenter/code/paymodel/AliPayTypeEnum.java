package cn.bootx.paymentcenter.code.paymodel;

import cn.bootx.common.web.exception.BizException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
* 支付宝支付方式
* @author xxm
* @date 2021/7/2
*/
@Getter
@AllArgsConstructor
public enum AliPayTypeEnum {

    /** wap支付 */
    WAP(AliPayCode.WAP,"WAP"),

    /** 应用支付 */
    APP(AliPayCode.APP,"APP"),

    /** web支付 */
    WEB(AliPayCode.WEB,"WEB"),

    /** 二维码扫码支付 */
    QRCODE(AliPayCode.QRCODE,"QRCODE"),

    /** 付款码支付 */
    BARCODE(AliPayCode.BARCODE,"BARCODE");

    /** 支付类型数字编码 */
    private final int no;
    /** 支付类型字符编码 */
    private final String code;

    /**
     * 根据no获取
     */
    public static AliPayTypeEnum findByNo(int no){
        return Arrays.stream(AliPayTypeEnum.values())
                .filter(e -> e.getNo() == no)
                .findFirst()
                .orElseThrow(() -> new BizException("不存在的支付no"));
    }

}
