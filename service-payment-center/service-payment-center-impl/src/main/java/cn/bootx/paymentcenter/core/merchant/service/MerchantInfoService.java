package cn.bootx.paymentcenter.core.merchant.service;

import cn.bootx.common.web.rest.PageResult;
import cn.bootx.common.web.rest.param.PageParam;
import cn.bootx.common.web.util.ResultConvertUtils;
import cn.bootx.paymentcenter.core.merchant.dao.MerchantInfoManager;
import cn.bootx.paymentcenter.core.merchant.dao.MerchantInfoRepository;
import cn.bootx.paymentcenter.core.merchant.entity.MerchantInfo;
import cn.bootx.paymentcenter.dto.merchant.MerchantInfoDto;
import cn.bootx.paymentcenter.param.merchant.MerchantInfoParam;
import cn.bootx.starter.jpa.utils.JpaUtils;
import cn.bootx.starter.snowflake.SnowFlakeId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 商户
* @author xxm  
* @date 2021/6/29 
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class MerchantInfoService {
    private final MerchantInfoManager merchantInfoManager;
    private final MerchantInfoRepository merchantInfoRepository;
    private final SnowFlakeId snowflakeId;

    /**
     * 添加商户
     */
    public MerchantInfoDto add(MerchantInfoParam param){
        MerchantInfo merchantInfo = MerchantInfo.init(param);
        // 商户号
        merchantInfo.setMerchantNo("M"+snowflakeId.nextId());
        return merchantInfoRepository.save(merchantInfo).toDto();
    }

    /**
     * 修改商户
     */
    @CacheEvict(value = "pc:merchant",allEntries = true)
    public MerchantInfoDto update(MerchantInfoParam param){
        MerchantInfo merchantInfo = MerchantInfo.init(param);
        // 商户号
        merchantInfo.setMerchantNo("M"+snowflakeId.nextId());
        return merchantInfoRepository.save(merchantInfo).toDto();
    }

    /**
     * 列表查询
     */
    public List<MerchantInfoDto> findAll(){
        return ResultConvertUtils.dtoListConvert(merchantInfoManager.findAll());
    }

    /**
     * 分页
     */
    public PageResult<MerchantInfoDto> page(PageParam pageParam,MerchantInfoParam param){
        return JpaUtils.convert2PageResult(merchantInfoManager.page(pageParam,param));
    }

    /**
     * 查询详情
     */
    public MerchantInfoDto findById(Long id){
        return merchantInfoManager.findById(id).map(MerchantInfo::toDto)
                .orElse(null);
    }
}
