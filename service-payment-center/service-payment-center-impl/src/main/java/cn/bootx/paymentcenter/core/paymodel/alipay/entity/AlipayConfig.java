package cn.bootx.paymentcenter.core.paymodel.alipay.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.paymentcenter.core.paymodel.alipay.convert.AlipayConvert;
import cn.bootx.paymentcenter.dto.paymodel.alipay.AlipayConfigDto;
import cn.bootx.starter.jpa.base.JpaBaseEntity;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;

/**   
* 支付宝支付配置
* @author xxm  
* @date 2020/12/15 
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Accessors(chain = true)
@Table(name = "pc_alipay_config")
public class AlipayConfig extends JpaBaseEntity implements EntityBaseFunction<AlipayConfigDto> {

    /** 名称 */
    private String name;

    /** 备注 */
    private String remark;

    /** 系统商户appId */
    private String appId;

    /** 支付宝商户appId */
    private String aliAppId;

    /** 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 */
    private String notifyUrl;

    /** 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址 */
    private String returnUrl;

    /** 请求网关地址 */
    private String serverUrl;

    /** 支付宝公钥 */
    public String alipayPublicKey;

    /** 私钥 */
    private String privateKey;

    /** 签名类型 */
    public String signType;

    /** 是否沙箱环境 */
    private boolean sandbox;

    /** 超时配置 */
    private String expireTime;

    /** 可用支付方式 */
    private String payTypes;

    /** 状态 */
    private Integer state;


    @Override
    public AlipayConfigDto toDto(){
        AlipayConfigDto convert = AlipayConvert.CONVERT.convert(this);
        convert.setPayTypeList(StrUtil.split(this.getPayTypes(),','));
        return convert;
    }

    public static AlipayConfig init(AlipayConfigDto dto){
        AlipayConfig convert = AlipayConvert.CONVERT.convert(dto);
        if (CollUtil.isNotEmpty(dto.getPayTypeList())){
            convert.setPayTypes(String.join(",", dto.getPayTypeList()));
        }
        return convert;
    }

}

