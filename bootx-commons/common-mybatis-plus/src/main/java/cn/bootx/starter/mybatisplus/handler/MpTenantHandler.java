package cn.bootx.starter.mybatisplus.handler;

import cn.bootx.common.core.code.CommonCode;
import cn.bootx.starter.headerholder.HeaderHolder;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.NullValue;

import java.util.Objects;

/**
 * sql租户解析器
 * @author xxm
 * @date 2020/4/14 15:28
 */
public class MpTenantHandler implements TenantHandler {

	private final HeaderHolder headerHolder;

	public MpTenantHandler(HeaderHolder headerHolder) {
		this.headerHolder = headerHolder;
	}


	/**
	 * 租户值
	 */
	@Override
	public Expression getTenantId(boolean where) {

		return Objects.isNull(headerHolder.getTid())?new NullValue():new LongValue(headerHolder.getTid());
	}

	/**
	 * 租户字段名称
	 */
	@Override
	public String getTenantIdColumn() {
		return CommonCode.TID;
	}

	@Override
	public boolean doTableFilter(String tableName) {
		//TODO 如果是超级租户 不进行过滤
		return Objects.equals(headerHolder.getTid(), 1L);
	}
}
