package cn.bootx.usercenter.code;

/**   
* 缓存名称
* @author xxm  
* @date 2021/6/16 
*/
public interface CachingCode {

    /** 网关请求对应权限缓存 */
    String PERMISSION_GATEWAY = "uc:permission:gateway";

    /** 角色权限关系缓存 */
    String ROLE_PERMISSION = "uc:role:permission";

    /** 用户权限id关系缓存 */
    String USER_PERMISSION_ID = "uc:user:permission:id";

    /** 用户权限id关系缓存 */
    String USER_PERMISSION = "uc:user:permission";
}