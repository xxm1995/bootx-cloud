package cn.bootx.paymentcenter.core.payconfig.service;

import cn.bootx.common.web.exception.BizException;
import cn.bootx.common.web.util.ResultConvertUtils;
import cn.bootx.paymentcenter.core.payconfig.dao.PayChannelManager;
import cn.bootx.paymentcenter.core.payconfig.dao.PayChannelWayManager;
import cn.bootx.paymentcenter.core.payconfig.dao.PayChannelWayRepository;
import cn.bootx.paymentcenter.core.payconfig.entity.PayChannel;
import cn.bootx.paymentcenter.core.payconfig.entity.PayChannelWay;
import cn.bootx.paymentcenter.dto.payconfig.PayChannelWayDto;
import cn.bootx.paymentcenter.param.payconfig.PayChannelWayParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**   
* 支付通道的支付方式
* @author xxm  
* @date 2021/6/30 
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class PayChannelWayService {
    private final PayChannelManager payChannelManager;
    private final PayChannelWayManager payChannelWayManager;
    private final PayChannelWayRepository payChannelRepository;

    /**
     * 添加支付方式
     */
    public PayChannelWayDto add(PayChannelWayParam param){
        PayChannel payChannel = payChannelManager.findById(param.getChannelId())
                .orElseThrow(() -> new BizException("支付渠道不存在"));
        if (this.existsByChannelAndCode(payChannel.getId(),param.getCode())){
            throw new BizException("重复的支付方式");
        }

        PayChannelWay payChannelWay = PayChannelWay.init(param);
        payChannelWay.setChannelCode(payChannel.getCode());
        return payChannelRepository.save(payChannelWay).toDto();
    }

    /**
     * 删除
     */
    public void delete(Long id){
        payChannelWayManager.deleteById(id);
    }

    /**
     * 查询支付通道下的支付方式
     */
    public List<PayChannelWayDto> findByChannel(Long channelId){
        return ResultConvertUtils.dtoListConvert(payChannelWayManager.findByChannel(channelId));
    }

    /**
     * 支付通道是否存在指定支付方式
     */
    public boolean existsByChannelAndCode(Long channelId, String code){
        return payChannelWayManager.existsByChannelAndCode(channelId,code);
    }
}
