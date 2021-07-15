package cn.bootx.iam.dto.login;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
* @author xxm
* @date 2021/7/12
*/
@Data
@Accessors(chain = true)
@ApiModel("路由菜单元信息")
public class LoginMenuMetaDto {

    /** 是否缓存页面 */
    private boolean keepAlive;

    /** 是否外链菜单打开方式 */
    private boolean internalOrExternal;

    /** 一级菜单跳转地址 */
    private String redirect;

    /** 标题 */
    private String title;

    /** 组件名称 */
    private String componentName;

    /** 图标 */
    private String icon;
}
