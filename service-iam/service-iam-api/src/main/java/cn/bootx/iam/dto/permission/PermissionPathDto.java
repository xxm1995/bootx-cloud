package cn.bootx.iam.dto.permission;

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
@ApiModel("权限资源")
public class PermissionPathDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = -8909568804160122987L;

    /** 权限的标识 */
    private String code;

    /** 权限对应的api路径 */
    private String path;

    /** 请求的http方法 */
    private String method;

    /** 权限的层级 */
    private String fdLevel;

    /** 权限描述 */
    private String description;

    /** 权限对应的方法名 */
    private String action;

    /** 权限资源类型 */
    private String fdResource;

    /** 权限所在的服务名称 */
    private String serviceName;

    /** 是否公开的权限 */
    private Boolean publicAccess;

    /** 是否需要登录才能访问的权限 */
    private Boolean loginAccess;

    /** 是否为内部接口 */
    private Boolean within;

    /** API标签 */
    private String tag;

}
