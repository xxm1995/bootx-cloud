package cn.bootx.noticecenter.dto.mail;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
* @author xxm
* @date 2020/5/2 14:42
*/
@Data
@ApiModel(value = "邮箱配置 DTO")
public class MailConfigDto implements Serializable {
    private static final long serialVersionUID = 2322690493233843789L;

    /**
     * 普通方式
     */
    public static final Integer SECURITY_TYPE_PLAIN = 1;

    /**
     * TLS方式
     */
    public static final Integer SECURITY_TYPE_TLS = 2;

    /**
     * SSL方式
     */
    public static final Integer SECURITY_TYPE_SSL = 3;


    @ApiModelProperty(name = "id", value = "主键")
    private Long id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty(name = "code", value = "编号")
    private String code;

    @ApiModelProperty(name = "host", value = "邮箱服务器 host")
    private String host;

    @ApiModelProperty(name = "port", value = "邮箱服务器 port")
    private Integer port;

    @ApiModelProperty(name = "username", value = "邮箱服务器 username")
    private String username;

    @ApiModelProperty(name = "password", value = "邮箱服务器 password")
    private String password;

    @ApiModelProperty(name = "sender", value = "邮箱服务器 sender")
    private String sender;

    @ApiModelProperty(name = "from", value = "邮箱服务器 from")
    private String from;

    @ApiModelProperty(name = "isDefault", value = "是否默认配置")
    private Boolean isDefault = false;

    @ApiModelProperty(value = "安全方式")
    private Integer securityType;
}
