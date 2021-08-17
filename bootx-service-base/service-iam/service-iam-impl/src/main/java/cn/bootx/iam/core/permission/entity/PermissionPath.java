package cn.bootx.iam.core.permission.entity;


import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.iam.core.permission.convert.PermissionConvert;
import cn.bootx.iam.dto.permission.PermissionPathDto;
import cn.bootx.iam.param.permission.PermissionPathParam;
import cn.bootx.common.jpa.base.JpaBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
* 权限资源(url请求)
* @author xxm  
* @date 2020/5/10 23:09 
*/
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Accessors(chain = true)
@Table(name="uc_permission_path")
public class PermissionPath extends JpaBaseEntity implements EntityBaseFunction<PermissionPathDto> {

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

    public static PermissionPath init(PermissionPathParam param){
        return PermissionConvert.CONVERT.convert(param);
    }

    public static PermissionPath init(PermissionPathDto dto){
        return PermissionConvert.CONVERT.convert(dto);
    }

    @Override
    public PermissionPathDto toDto() {
        return PermissionConvert.CONVERT.convert(this);
    }
}
