package cn.bootx.gateway.helper.properties;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
* 助手配置
* @author xxm
* @date 2021/6/1
*/
@Data
@Accessors(chain = true)
@RefreshScope
@ConfigurationProperties(prefix = "bootx.gateway.helper")
public class GatewayHelperProperties {

    /** 权限 */
    private Permission permission = new Permission();

    /** 签名 */
    private Signature signature = new Signature();

    /** 是否开启 */
    private boolean enabled = true;

    /** 幂等 */
    private boolean retryable = false;

    /** 启用 Jwt 日志 */
    private boolean enabledJwtLog = false;

    /** jwt key */
    private String jwtKey = "bootx";

    /** 跳过路径 */
    private String[] helperSkipPaths;

    /** 过滤器 */
    private Filter filter = new Filter();


    /**   
    * 权限
    * @author xxm  
    * @date 2021/6/1 
    */
    @Data
    @Accessors(chain = true)
    public static class Permission {
        private Boolean enabled = true;

        /** 跳过路径 */
        private List<String> skipPaths = Arrays.asList("/**/skip/**", "/oauth/**");

        /** 内部路径 */
        private List<String> internalPaths = Arrays.asList("/oauth/admin/**", "/oauth/api/**");

        /** 缓存(秒) */
        private Long cacheSeconds = 600L;

        /** 缓存大小 */
        private Long cacheSize = 5000L;

    }

    /**   
    * 签名
    * @author xxm  
    * @date 2021/6/1 
    */
    @Data
    @Accessors(chain = true)
    public static class Signature {
        /**
         * 是否启用签名验证
         */
        private boolean enabled = false;
        /**
         * 签名加密客户端信息
         */
        private List<Secret> secrets = new ArrayList<>();
        /**
         * 签名过期时间，单位秒，默认600秒
         */
        private int expireTime = 600;

        /**
         * 签名API标签
         */
        private String signLabel = "SIGN_API";
    }

    /**   
    * 加密
    * @author xxm  
    * @date 2021/6/1 
    */
    @Data
    @Accessors(chain = true)
    public static class Secret {
        private String secretId;
        private String secretKey;

    }
    
    /**   
    * 过滤器
    * @author xxm  
    * @date 2021/6/1 
    */
    @Data
    @Accessors(chain = true)
    public static class Filter {

        /** 收集跨度 */
        private CollectSpan collectSpan = new CollectSpan();

        /** 公共请求配置 */
        private CommonRequest commonRequest = new CommonRequest();

        /** 菜单权限 */
        private MenuPermission menuPermission = new MenuPermission();

        /** 接口重放配置 */
        private ApiReplay apiReplay = new ApiReplay();

        /** 租户过滤配置 */
        private Tenant tenant = new Tenant();

        /**
        * 收集跨度
        * @author xxm
        * @date 2021/6/1
        */
        @Data
        @Accessors(chain = true)
        public static class CollectSpan {
            /**
             * 是否启用API统计
             */
            private boolean enabled = true;
        }
        
        /**   
        * 公共请求配置
        * @author xxm  
        * @date 2021/6/1 
        */
        @Data
        @Accessors(chain = true)
        public static class CommonRequest {
            /**
             * 是否校验用户角色权限
             */
            private boolean enabled = true;
            /**
             * 是否校验用户有此租户的访问权限
             */
            private boolean checkTenant = true;
            /**
             * 是否校验用户有此项目的访问权限
             */
            private boolean checkProject = true;
            /**
             * 是否仅校验当前角色有此权限，否则只要有一个角色有权限即可
             */
            private boolean checkCurrentRole = false;
            /**
             * URI路径上租户ID参数
             */
            private List<String> parameterTenantId = Arrays.asList("organization_id", "organizationId", "tenantId");
            /**
             * URI路径上项目ID参数
             */
            private List<String> parameterProjectId = Arrays.asList("project_id", "projectId");

        }

        /**
        * 菜单权限
        * @author xxm
        * @date 2021/6/1
        */
        @Data
        @Accessors(chain = true)
        public static class MenuPermission {
            /** 是否启用菜单权限校验 */
            private boolean enabled = false;

        }
        /**
        * 接口重放配置
        * @author xxm
        * @date 2021/6/1
        */
        @Data
        @Accessors(chain = true)
        public static class ApiReplay {
            /** 是否启用api防重放校验 */
            private boolean enabled = false;
            /** 超时时间, 重试 */
            private int timeoutOfOpToken = 3 * 10 * 1000;
            /** 跳过路径 */
            private List<String> skipPaths = new ArrayList<>();
        }

        /**   
        * 租户配置
        * @author xxm  
        * @date 2021/7/14 
        */
        @Data
        @Accessors(chain = true)
        public static class Tenant {
            /** 是否启用 */
            private boolean enabled = false;
        }
    }
}
