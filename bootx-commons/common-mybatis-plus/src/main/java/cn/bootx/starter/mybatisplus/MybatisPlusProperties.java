package cn.bootx.starter.mybatisplus;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
*
* @author xxm
* @date 2020/4/15 15:11
*/

@ConfigurationProperties(prefix = "bootx.starter.mybatis-plus")
public class MybatisPlusProperties {

	private boolean enabled;
	private boolean optimisticLocker;
	private boolean tenantHandler;

	public boolean isEnabled() {
		return enabled;
	}

	public MybatisPlusProperties setEnabled(boolean enabled) {
		this.enabled = enabled;
		return this;
	}

	public boolean isOptimisticLocker() {
		return optimisticLocker;
	}

	public MybatisPlusProperties setOptimisticLocker(boolean optimisticLocker) {
		this.optimisticLocker = optimisticLocker;
		return this;
	}

	public boolean isTenantHandler() {
		return tenantHandler;
	}

	public MybatisPlusProperties setTenantHandler(boolean tenantHandler) {
		this.tenantHandler = tenantHandler;
		return this;
	}
}
