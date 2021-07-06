package cn.bootx.bsp.client;

import cn.bootx.bsp.dto.tenant.TenantDto;
import cn.bootx.common.web.anno.Idempotent;
import cn.bootx.common.web.rest.PageResult;
import cn.bootx.common.web.rest.param.PageParam;

import java.util.List;

/**   
* 租户client
* @author xxm  
* @date 2020/4/23 21:29 
*/
public interface TenantClient {
	/**
	 * 注册新租户
	 */
	TenantDto register(TenantDto tenantDto);

	/**
	 * 更新租户基本信息
	 */
	TenantDto update(TenantDto tenantDto);

	/**
	 * 更新租户加密类型信息
	 */
	TenantDto updateEncryptModel(Long id, Integer encryptModel);

	/**
	 * 租户是否有效
	 */
	boolean isTenantValid(Long tenantId);

	/**
	 * 激活租户
	 */
	TenantDto activateTenant(Long id);

	/**
	 * 禁用租户
	 */
	TenantDto forbiddenTenant(Long id);

	/**
	 * 判断租户是否已存在
	 */
	boolean isTenantIdExisted(Long id);

	/**
	 * 判断租户name是否已存在
	 */
	boolean isTenantNameExisted(String name);

	/**
	 * 判断租户code是否已存在
	 */
	boolean isTenantCodeExisted(String code);

	/**
	 * 根据id获取租户
	 */
	TenantDto findById(Long id);
	/**
	 * 获取租户
	 */
	TenantDto findTenant();

	/**
	 * 根据 名称 查询
	 */
	TenantDto findByName(String name);

	/**
	 * 根据 code 查询
	 */
	TenantDto findByCode(String code);

	/**
	 * 根据ID组查找租户
	 */
	List<TenantDto> findByIds(List<Long> ids);

	/**
	 * 获取所有租户
	 */
	@Idempotent
	List<TenantDto> findAll();

	/**
	 * 分页获取租户
	 */
	PageResult<TenantDto> page(PageParam pageParam);

	/**
	 * 删除租户
	 */
	void delete(Long id);

}
