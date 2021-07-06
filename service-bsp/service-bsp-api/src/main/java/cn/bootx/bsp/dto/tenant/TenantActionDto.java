package cn.bootx.bsp.dto.tenant;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**   
* 租户变动信息
* @author xxm  
* @date 2020/4/23 10:56
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TenantActionDto  implements Serializable {

	private static final long serialVersionUID = -5177507615832250030L;

	public static final int ACTION_ADD = 0;
	public static final int ACTION_UPDATE = 1;
	public static final int ACTION_DEL = 2;

	@ApiModelProperty("0：新增，1：修改，2：逻辑删除")
	private int action;

	@ApiModelProperty("请求发起的app主键")
	private Long appId;

	@ApiModelProperty("租户信息")
	private TenantDto tenantDto;

}
