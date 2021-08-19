package cn.bootx.paymentcenter.dto.paymodel.wechat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
* @author xxm
* @date 2021/3/19
*/
@Data
@Accessors(chain = true)
@ApiModel("微信支付配置")
public class WeChatPayConfigDto implements Serializable {

    @ApiModelProperty("主键")
    private Long id;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("系统商户appId")
    private String appId;
    @ApiModelProperty("微信应用Id")
    private String wxAppId;
    @ApiModelProperty("微信商户号")
    private String mchId;
    @ApiModelProperty("微信服务商应用编号")
    private String slAppId;
    @ApiModelProperty("微信服务商商户号")
    private String slMchId;
    @ApiModelProperty("同 apiKey 后续版本会舍弃")
    private String partnerKey;
    @ApiModelProperty("商户平台「API安全」中的 API 密钥")
    private String apiKey;
    @ApiModelProperty("商户平台「API安全」中的 APIv3 密钥")
    private String apiKey3;
    @ApiModelProperty("应用域名，回调中会使用此参数")
    private String domain;
    @ApiModelProperty("服务器异步通知页面路径")
    private String notifyUrl;
    @ApiModelProperty("页面跳转同步通知页面路径")
    private String returnUrl;
    @ApiModelProperty("API 证书中的 p12")
    private String certPath;
    @ApiModelProperty("API 证书中的 key.pem")
    private String keyPemPath;
    @ApiModelProperty("API 证书中的 cert.pem")
    private String certPemPath;
    @ApiModelProperty("是否沙箱环境")
    private boolean sandbox;
    @ApiModelProperty("可用支付方式")
    private String payTypes;
    @ApiModelProperty("可用支付方式")
    private List<String> payTypeList;
    @ApiModelProperty("状态")
    private Integer state;
}
