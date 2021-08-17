package cn.bootx.paymentcenter.core.payconfig.service;

import cn.bootx.common.core.exception.BizException;
import cn.bootx.common.core.rest.PageResult;
import cn.bootx.common.core.rest.param.PageParam;
import cn.bootx.common.core.util.ResultConvertUtils;
import cn.bootx.paymentcenter.code.pay.PayTypeEnum;
import cn.bootx.paymentcenter.core.payconfig.dao.PayChannelManager;
import cn.bootx.paymentcenter.core.payconfig.dao.PayChannelRepository;
import cn.bootx.paymentcenter.core.payconfig.entity.PayChannel;
import cn.bootx.paymentcenter.dto.payconfig.PayChannelDto;
import cn.bootx.paymentcenter.param.payconfig.PayChannelParam;
import cn.bootx.common.jpa.utils.JpaUtils;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 支付渠道
 * @author xxm
 * @date 2021/6/30
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PayChannelService {
    private final PayChannelManager payChannelManager;
    private final PayChannelRepository payChannelRepository;

    /**
     * 添加支付渠道
     */
    public PayChannelDto add(PayChannelParam param){
        // 是否合法的支付方式code
        if (!PayTypeEnum.existsByCode(param.getCode())){
            throw new BizException("非法的支付方式code");
        }
        if (this.existsByCode(param.getCode())){
            throw new BizException("支付通道已经存在");
        }
        PayChannel payChannel = PayChannel.init(param);
        return payChannelRepository.save(payChannel).toDto();
    }

    /**
     * 修改
     */
    public PayChannelDto update(PayChannelParam param){
        param.setCode(null);
        PayChannel payChannel = payChannelManager.findById(param.getId()).orElseThrow(() -> new BizException("支付通道不存在"));
        BeanUtil.copyProperties(param,payChannel, CopyOptions.create().ignoreNullValue());
        return payChannelRepository.save(payChannel).toDto();
    }

    /**
     * 根据id获取
     */
    public PayChannelDto findById(Long id){
        return payChannelManager.findById(id).map(PayChannel::toDto)
                .orElse(null);
    }

    /**
     * 根据code查询
     */
    public PayChannelDto findByCode(String code){
        return payChannelManager.findByCode(code).map(PayChannel::toDto)
                .orElse(null);
    }

    /**
     * 获取支付方式编码
     */
    public List<String> findPayTypeCodes(){
        List<String> collect = payChannelManager.findAll().stream()
                .map(PayChannel::getCode)
                .collect(Collectors.toList());
        return PayTypeEnum.findPayTypes().stream()
                .filter(s->!collect.contains(s))
                .collect(Collectors.toList());
    }

    /**
     * 查询全部
     */
    public List<PayChannelDto> findAll(){
        return ResultConvertUtils.dtoListConvert(payChannelManager.findAll());
    }

    /**
     * 查询全部
     */
    public PageResult<PayChannelDto> page(PageParam pageParam,PayChannelParam param){
        return JpaUtils.convert2PageResult(payChannelManager.page(pageParam,param));
    }

    /**
     * 判断是否已经有该支付渠道
     */
    public boolean existsByCode(String code){
        return payChannelManager.existsByCode(code);
    }
}
