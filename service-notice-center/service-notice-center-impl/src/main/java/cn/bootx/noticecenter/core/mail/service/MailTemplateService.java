package cn.bootx.noticecenter.core.mail.service;

import cn.bootx.common.core.rest.PageResult;
import cn.bootx.common.core.rest.param.PageParam;
import cn.bootx.noticecenter.core.mail.dao.MailTemplateManager;
import cn.bootx.noticecenter.core.mail.dao.MailTemplateRepository;
import cn.bootx.noticecenter.core.mail.entity.MailTemplate;
import cn.bootx.noticecenter.dto.mail.MailTemplateDto;
import cn.bootx.noticecenter.exception.CodeTemplateExistedException;
import cn.bootx.noticecenter.exception.MailTemplateNotExistException;
import cn.bootx.common.jpa.utils.JpaUtils;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * 邮件模板
 * @author xxm
 * @date 2020/6/8 22:01
 */
@Slf4j
@Service
@AllArgsConstructor
public class MailTemplateService {
    private final MailTemplateManager mailTemplateManager;
    private final MailTemplateRepository mailTemplateRepository;

    /**
     * 新增邮件模板
     */
    public MailTemplateDto addTemplate(MailTemplateDto templateDto){
        // check code
        if (mailTemplateManager.existsByCode(templateDto.getCode())){
            throw new CodeTemplateExistedException();
        }

        MailTemplate mailTemplate = MailTemplate.init(templateDto);
        return mailTemplateRepository.save(mailTemplate).toDto();
    }

    /**
     * 更新模板配置
     */
    public MailTemplateDto updateTemplate(MailTemplateDto templateDto) {

        if (mailTemplateManager.existsByCode(templateDto.getCode(),templateDto.getId())){
            throw new CodeTemplateExistedException();
        }
        // 获取原数据
        MailTemplate mailTemplate = mailTemplateManager.findById(templateDto.getId())
                .orElseThrow(MailTemplateNotExistException::new);
        BeanUtil.copyProperties(templateDto,mailTemplate, CopyOptions.create().ignoreNullValue());

        return mailTemplateRepository.save(mailTemplate).toDto();
    }

    /**
     * 模板列表
     */
    public PageResult<MailTemplateDto> page(PageParam param){
        Page<MailTemplate> page = mailTemplateManager.page(param);
        return JpaUtils.convert2PageResult(page);
    }

    /**
     * 根据id删除
     */
    public void deleteById(Long id){
        mailTemplateManager.deleteById(id);
    }

}
