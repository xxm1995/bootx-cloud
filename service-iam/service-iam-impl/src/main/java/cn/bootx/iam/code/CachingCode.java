package cn.bootx.iam.code;

/**
* 缓存名称
* @author xxm  
* @date 2021/6/16 
*/
public interface CachingCode {

    /** 请求对应权限缓存 */
    String PERMISSION_PATH = "uc:permission:path";

    /** 菜单对应权限缓存 */
    String PERMISSION_MENU = "uc:permission:menu";

    /** 用户权限id关系缓存 */
    String USER_PERMISSION_ID = "uc:user:permission:id";

    /** 用户请求权限id关系缓存 */
    String USER_PATH_ID = "uc:user:path:id";

    /** 用户菜单权限id关系缓存 */
    String USER_MENU_ID = "uc:user:menu:id";

    /** 用户请求权限关系缓存 */
    String USER_PATH = "uc:user:path";

    /** 用户菜单权限关系缓存 */
    String USER_MENU = "uc:user:menu";
}
