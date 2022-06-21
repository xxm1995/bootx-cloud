package cn.bootx.iam.dto.role;

import cn.bootx.common.web.rest.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author xxm
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@ApiModel("角色")
public class RoleDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = 5532196699667233754L;

    /** 角色code */
    private String code;

    /** 角色名称 */
    private String name;

    /** 描述 */
    private String description;

}
