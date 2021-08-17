package cn.bootx.iam.core.permission.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PermissionPathImport {

    /** 权限的标识 */
    @Excel(name = "标识")
    private String code;

    /** 权限对应的api路径 */
    @Excel(name = "权限对应的api路径")
    private String path;

    /** 请求的http方法 */
    @Excel(name = "请求的http方法")
    private String method;

    /** 权限的层级 */
    @Excel(name = "权限的层级")
    private String fdLevel;

    /** 权限描述 */
    @Excel(name = "权限描述")
    private String description;

    /** 权限对应的方法名 */
    @Excel(name = "权限对应的方法名")
    private String action;

    /** 权限资源类型 */
    @Excel(name = "权限资源类型")
    private String fdResource;

    /** 权限所在的服务名称 */
    @Excel(name = "权限所在的服务名称")
    private String serviceName;

    /** 是否公开的权限 */
    @Excel(name = "是否公开的权限")
    private Boolean publicAccess;

    /** 是否需要登录才能访问的权限 */
    @Excel(name = "是否需要登录才能访问的权限")
    private Boolean loginAccess;

    /** 是否为内部接口 */
    @Excel(name = "是否为内部接口")
    private Boolean within;

    /** API标签 */
    @Excel(name = "API标签")
    private String tag;
}
