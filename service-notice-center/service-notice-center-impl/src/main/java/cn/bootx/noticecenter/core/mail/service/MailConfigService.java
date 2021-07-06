package cn.bootx.noticecenter.core.mail.service;

import cn.bootx.noticecenter.core.mail.dao.MailConfigManager;
import cn.bootx.noticecenter.core.mail.dao.MailConfigRepository;
import cn.bootx.noticecenter.core.mail.entity.MailConfig;
import cn.bootx.noticecenter.dto.mail.MailConfigDto;
import cn.bootx.noticecenter.exception.DefaultMailConfigAlreadyExistedException;
import cn.bootx.noticecenter.exception.MailConfigCodeAlreadyExistedException;
import cn.bootx.noticecenter.exception.MailConfigNotExistException;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 邮件配置
 * @author xxm
 * @date 2020/4/8 13:29
 */
@Service
@AllArgsConstructor
public class MailConfigService {
    private final MailConfigManager mailConfigManager;
    private final MailConfigRepository mailConfigRepository;

    /**
     * 获取指定租户下的所有邮箱配置
     */
    public List<MailConfigDto> findAll() {
        List<MailConfig> categories = mailConfigManager.findAll();
        return categories.stream().map(MailConfig::toDto).collect(Collectors.toList());
    }

    /**
     * 根据 id 获取相应的邮箱配置
     */
    public MailConfigDto getById(Long id) {
        return mailConfigManager.findById(id).map(MailConfig::toDto).orElse(null);
    }

    /**
     * 获取 默认邮箱配置
     */
    public MailConfigDto getDefaultConfig() {
        return mailConfigManager.findByDefault().map(MailConfig::toDto).orElse(null);
    }

    /**
     * 根据 code 获取相应的邮箱配置
     */
    public MailConfigDto getByCode(String code) {
        return mailConfigManager.findByCode(code).map(MailConfig::toDto).orElse(null);
    }

    /**
     * 添加新邮箱配置
     */
    @Transactional(rollbackFor = Exception.class)
    public MailConfigDto addMailConfig(MailConfigDto dto) {
        if (mailConfigManager.existsByCode(dto.getCode())) {
            throw new MailConfigCodeAlreadyExistedException();
        }

        // 每个租户下只能有一个默认配置
        if(dto.getIsDefault()&& mailConfigManager.existsByDefault()) {
            throw new DefaultMailConfigAlreadyExistedException();
        }

        MailConfig mailConfig = MailConfig.init(dto);
        ;

        return mailConfigRepository.save(mailConfig).toDto();
    }

    /**
     * 更新邮箱配置
     */
    @Transactional(rollbackFor = Exception.class)
    public MailConfigDto update(MailConfigDto mailConfigDto) {
        MailConfig mailConfig = mailConfigManager.findById(mailConfigDto.getId()).orElseThrow(MailConfigNotExistException::new);

        BeanUtil.copyProperties(mailConfigDto,mailConfig, CopyOptions.create().ignoreNullValue());

        return mailConfigRepository.save(mailConfig).toDto();
    }

    /**
     * 根据 id 删除相应的邮箱配置
     */
    public void deleteById(Long id) {
        mailConfigManager.deleteById(id);
    }
}
