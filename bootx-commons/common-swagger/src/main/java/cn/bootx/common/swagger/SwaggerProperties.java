package cn.bootx.common.swagger;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
* swagger配置
* @author xxm
* @date 2020/4/9 13:36
*/
@ConfigurationProperties(prefix = "bootx.starter.swagger")
public class SwaggerProperties {

    private boolean enabled;
    private String basePackage;
    private String title;
    private String description;
    private String termsOfServiceUrl;
    private String version;

    public boolean isEnabled() {
        return enabled;
    }

    public SwaggerProperties setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public SwaggerProperties setBasePackage(String basePackage) {
        this.basePackage = basePackage;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public SwaggerProperties setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SwaggerProperties setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getTermsOfServiceUrl() {
        return termsOfServiceUrl;
    }

    public SwaggerProperties setTermsOfServiceUrl(String termsOfServiceUrl) {
        this.termsOfServiceUrl = termsOfServiceUrl;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public SwaggerProperties setVersion(String version) {
        this.version = version;
        return this;
    }
}
