package cn.bootx.paymentcenter.core.merchant.service;

import cn.bootx.common.web.exception.BizException;
import cn.bootx.common.web.util.ResultConvertUtils;
import cn.bootx.paymentcenter.core.merchant.dao.AppChannelManager;
import cn.bootx.paymentcenter.core.merchant.dao.MerchantAppManager;
import cn.bootx.paymentcenter.core.merchant.dao.MerchantAppRepository;
import cn.bootx.paymentcenter.core.merchant.dao.MerchantInfoManager;
import cn.bootx.paymentcenter.core.merchant.entity.AppChannel;
import cn.bootx.paymentcenter.core.merchant.entity.MerchantApp;
import cn.bootx.paymentcenter.core.merchant.entity.MerchantInfo;
import cn.bootx.paymentcenter.core.payconfig.dao.PayChannelManager;
import cn.bootx.paymentcenter.core.payconfig.entity.PayChannel;
import cn.bootx.paymentcenter.dto.merchant.MerchantAppDto;
import cn.bootx.paymentcenter.dto.payconfig.PayChannelDto;
import cn.bootx.paymentcenter.param.merchant.MerchantAppParam;
import cn.bootx.starter.snowflake.SnowFlakeId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 商户应用
 * @author xxm
 * @date 2021/6/29
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MerchantAppService {
    private final MerchantAppManager merchantAppManager;
    private final MerchantInfoManager merchantInfoManager;
    private final PayChannelManager payChannelManager;
    private final AppChannelManager appChannelManager;

    private final MerchantAppRepository merchantAppRepository;
    private final SnowFlakeId snowFlakeId;

    /**
     * 添加应用
     */
    public MerchantAppDto add(MerchantAppParam param){
        MerchantApp merchantApp = MerchantApp.init(param);
        // 获取商户
        MerchantInfo merchantInfo = merchantInfoManager.findById(param.getMerchantId())
                .orElseThrow(() -> new BizException("商户不存在"));
        merchantApp.setMerchantNo(merchantInfo.getMerchantNo())
                .setAppId(snowFlakeId.nextIdStr());
        return merchantAppRepository.save(merchantApp).toDto();
    }

    /**
     * 查询应用
     */
    public List<MerchantAppDto> findByMerchantId(Long merchantId){
        return ResultConvertUtils.dtoListConvert(merchantAppManager.findByMerchantId(merchantId));
    }

    /**
     * 查询应用详情
     */
    public MerchantAppDto findById(Long id){
        return merchantAppManager.findById(id).map(MerchantApp::toDto)
                .orElse(null);
    }

    /**
     * 支持的支付方式
     */
    public List<PayChannelDto> findAppChannels(String appId){
        if (!merchantAppManager.existsByAppId(appId)){
            throw new BizException("商户应用不存在");
        }
        // 查询所有的支付
        List<PayChannel> payChannels = payChannelManager.findAll();
        Map<String, Integer> channelCodes = appChannelManager.findByAppId(appId).stream()
                .collect(Collectors.toMap(AppChannel::getCode, AppChannel::getState));

        return payChannels.stream()
                .map(PayChannel::toDto)
                .peek(o -> o.setState(channelCodes.get(o.getCode())))
                .collect(Collectors.toList());
    }

    /**
     * 验证
     */
    public boolean validationMerchantApp(String merchantNo, String appId) {
        return false;
    }
}
