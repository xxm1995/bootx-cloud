package cn.bootx.noticecenter.core.dingtalk.robot.service;

import cn.bootx.common.web.exception.BizException;
import cn.bootx.common.web.util.ResultConvertUtils;
import cn.bootx.noticecenter.core.dingtalk.robot.dao.DingRobotConfigManage;
import cn.bootx.noticecenter.core.dingtalk.robot.dao.DingRobotConfigRepository;
import cn.bootx.noticecenter.core.dingtalk.robot.entity.DingRobotConfig;
import cn.bootx.noticecenter.dto.dingtalk.DingRobotConfigDto;
import cn.bootx.noticecenter.exception.MailConfigNotExistException;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 钉钉机器人消息发送
 * @author xxm
 * @date 2020/11/29
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DingRobotConfigService {

    private final DingRobotConfigManage dingRobotConfigManage;
    private final DingRobotConfigRepository dingRobotConfigRepository;

    /**
     * 获取指定租户下的所有配置
     */
    public List<DingRobotConfigDto> findAll(){
        return ResultConvertUtils.dtoListConvert(dingRobotConfigManage.findAll());
    }

    /**
     * 根据 id 获取相应的配置
     */
    public DingRobotConfigDto getById(Long id) {
        return dingRobotConfigManage.findById(id).map(DingRobotConfig::toDto).orElse(null);
    }


    /**
     * 根据 code 获取相应的配置
     */
    public DingRobotConfigDto getByCode(String code) {
        return dingRobotConfigManage.findByCode(code).map(DingRobotConfig::toDto).orElse(null);
    }

    /**
     * 添加新配置
     */
    @Transactional(rollbackFor = Exception.class)
    public DingRobotConfigDto addConfig(DingRobotConfigDto dto) {
        if (dingRobotConfigManage.existsByCode(dto.getCode())) {
            throw new BizException("code重复");
        }

        DingRobotConfig dingRobotConfig = DingRobotConfig.init(dto);

        return dingRobotConfigRepository.save(dingRobotConfig).toDto();
    }

    /**
     * 更新邮箱配置
     */
    @Transactional(rollbackFor = Exception.class)
    public DingRobotConfigDto update(DingRobotConfigDto dto) {
        DingRobotConfig dingRobotConfig = dingRobotConfigManage.findById(dto.getId()).orElseThrow(MailConfigNotExistException::new);

        BeanUtil.copyProperties(dto,dingRobotConfig, CopyOptions.create().ignoreNullValue());

        return dingRobotConfigRepository.save(dingRobotConfig).toDto();
    }

    /**
     * 根据 id 删除相应的邮箱配置
     */
    public void deleteById(Long id) {
        dingRobotConfigManage.deleteById(id);
    }


}
