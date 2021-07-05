package cn.bootx.starter.mybatisplus;

import cn.bootx.starter.headerholder.HeaderHolder;
import cn.bootx.starter.mybatisplus.handler.MpTenantHandler;
import cn.bootx.starter.mybatisplus.handler.MpTenantParserHandler;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**   
* MybatisPlus自动配置(bootx是避免与mp的自动配置重名)
* @author xxm  
* @date 2020/4/15 14:39 
*/
@Configuration
@SuppressWarnings("all")
@ConditionalOnProperty(prefix = "bootx.starter.mybatis-plus",
		value = "enabled",
		havingValue = "true",
		matchIfMissing = true)
@ConditionalOnClass({SqlSessionFactory.class, SqlSessionFactoryBean.class})
@EnableConfigurationProperties(MybatisPlusProperties.class)
public class BootxMybatisPlusAutoConfiguration {

	/**
	 * 乐观锁
	 */
	@Bean
	@ConditionalOnProperty(prefix = "bootx.starter.mybatis-plus",
			value = "optimistic-locker",
			havingValue = "true")
	@ConditionalOnMissingBean(OptimisticLockerInterceptor.class)
	public OptimisticLockerInterceptor optimisticLockerInterceptor() {
		return new OptimisticLockerInterceptor();
	}

	@Bean
	@ConditionalOnMissingBean(TenantHandler.class)
	@ConditionalOnProperty(prefix = "bootx.starter.mybatis-plus",
			value = "tenant-handler",
			havingValue = "true")
	public MpTenantHandler mpTenantHandler(HeaderHolder headerHolder){
		return new MpTenantHandler(headerHolder);
	}

	/**
	 * 租户处理
	 */
	@Bean
	@ConditionalOnMissingBean(PaginationInterceptor.class)
	@ConditionalOnProperty(prefix = "bootx.starter.mybatis-plus",
			name = "tenant-handler",
			havingValue = "true")
	public PaginationInterceptor paginationInterceptor(MpTenantHandler mpTenantHandler) {
		PaginationInterceptor paginationInterceptor = new PaginationInterceptor();

		List<ISqlParser> sqlParserList = new ArrayList<>();
		TenantSqlParser tenantSqlParser = new MpTenantParserHandler();
		// 解析处理拦截器
		tenantSqlParser.setTenantHandler(mpTenantHandler);

		sqlParserList.add(tenantSqlParser);
		paginationInterceptor.setSqlParserList(sqlParserList);
		return paginationInterceptor;
	}
}
