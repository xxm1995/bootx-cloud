package cn.bootx.noticecenter.controller;

import cn.bootx.common.core.rest.Res;
import cn.bootx.common.core.rest.ResResult;
import cn.bootx.common.core.util.ValidationUtil;
import cn.bootx.noticecenter.code.ErrorCodes;
import cn.bootx.noticecenter.core.mail.service.MailConfigService;
import cn.bootx.noticecenter.dto.mail.MailConfigDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @author xxm
* @date 2020/5/2 14:38
*/
@Api(tags = "邮箱配置")
@RestController
@RequestMapping("/mail/config")
@AllArgsConstructor
public class MailConfigController {
    private final MailConfigService mailConfigService;

    @ApiOperation(value = "获取所有邮箱配置")
    @GetMapping(value = "/all")
    public ResResult<List<MailConfigDto>> getMailConfigList() {
        List<MailConfigDto> all = mailConfigService.findAll();
        return Res.ok(all);
    }

    @ApiOperation(value = "通过 id 获取指定邮箱配置")
    @GetMapping()
    public ResResult<MailConfigDto> getMailConfig(Long id) {
        MailConfigDto mailConfig = mailConfigService.getById(id);
        return Res.ok(mailConfig);
    }
    @ApiOperation(value = "增加新邮箱配置")
    @ApiResponses(value = {
            @ApiResponse(code = ErrorCodes.MAIL_CONFIG_CODE_ALREADY_EXISTED, message = "邮件 Config Code 已存在"),
            @ApiResponse(code = ErrorCodes.DEFAULT_MAIL_CONFIG_ALREADY_EXISTED, message = "默认邮箱配置已存在")
    })
    @PostMapping(value = "/add")
    public ResResult<MailConfigDto> addMailConfig(MailConfigDto mailConfigDto) {
        ValidationUtil.validateParam(mailConfigDto);
        MailConfigDto mailConfig = mailConfigService.addMailConfig(mailConfigDto);
        return Res.ok(mailConfig);
    }

    @ApiOperation(value = "更新邮箱配置")
    @PostMapping(value = "/update")
    public ResResult<MailConfigDto> updateMailConfig(@RequestBody MailConfigDto mailConfigDto) {
        ValidationUtil.validateParam(mailConfigDto);
        MailConfigDto update = mailConfigService.update(mailConfigDto);
        return Res.ok(update);
    }

    @ApiOperation(value = "通过 id 删除邮箱配置")
    @DeleteMapping(value = "/delete")
    public ResResult<Boolean> deleteMailConfigById1(Long id) {
        mailConfigService.deleteById(id);
        return Res.ok(true);
    }
}
