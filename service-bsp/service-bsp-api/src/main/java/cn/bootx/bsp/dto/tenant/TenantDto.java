package cn.bootx.bsp.dto.tenant;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**   
* 租户
* @author xxm  
* @date 2020/4/16 21:44 
*/
@Data
public class TenantDto implements Serializable {
    private static final long serialVersionUID = 7863146635572030216L;

    public static final int NOTICE_ENV_TEST = 1;
	public static final int NOTICE_ENV_NORMAL = 2;

	public static final int ACTIVE_STATE_YES = 1;
	public static final int ACTIVE_STATE_NO = 0;




    private Long id;

	@ApiModelProperty(name = "name", value = "租户名称", required = true)
	@NotBlank(message = "name null")
	private String name;

	@ApiModelProperty(name = "displayName", value = "租户展示名称")
	private String displayName;

	@ApiModelProperty(name = "activeState", value = "激活状态，0：未激活，1：已激活，默认0")
	private int activeState;

	@ApiModelProperty(name = "activeDate", value = "激活时间")
	private LocalDateTime activeDate;

	@ApiModelProperty(name = "code", value = "租户code")
	private String code;

	@ApiModelProperty(name = "contact", value = "联系人")
	private String contact;

	@ApiModelProperty(name = "phone", value = "手机")
	private String phone;

	@ApiModelProperty(name = "email", value = "邮箱")
	private String email;

	@ApiModelProperty(name = "noticeEnv", value = "发消息环境，1：测试，2：正式")
	private Integer noticeEnv;

	@ApiModelProperty(name = "encryptModel", value = "加密类型: 0 不加密;1 MD5;2 SHA-1, 入库默认0")
	private Integer encryptModel;

	@ApiModelProperty(name = "是否允许清理,true:允许，false：不允许")
	private Boolean allowClear;

	@ApiModelProperty(name = "dotNumber")
	private String dotNumber;

	@ApiModelProperty(name = "街道")
	private String street;

	@ApiModelProperty(name = "城市")
	private String city;

	@ApiModelProperty(name = "国家")
	private String country;

	@ApiModelProperty(name = "邮政编码")
	private String zipCode;

	@ApiModelProperty("附加信息(存json)")
	@Size(max = 1800, message = "addition max length is 1800")
	private String addition;

	@ApiModelProperty("创建人的name")
	private String creatorName;

	@ApiModelProperty("创建人的id")
	private Long creatorId;
}
