package cn.bootx.iam.dto.role;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author xxm
*/
@Data
@Accessors(chain = true)
@ApiModel("角色")
public class RoleDto implements Serializable {
    private static final long serialVersionUID = 5532196699667233754L;

    /** 角色id */
    private Long id;

    /** 角色code */
    private String code;

    /** 角色名称 */
    private String name;

    /** 描述 */
    private String description;

}
