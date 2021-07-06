package cn.bootx.usercenter.param.permission;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author xxm
* @date 2021/6/16
*/
@Data
@Accessors(chain = true)
@ApiModel("权限信息参数")
public class PermissionParam implements Serializable {
    private static final long serialVersionUID = 7617748080025164673L;

    /** 主键 */
    private Long id;

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
