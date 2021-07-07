package cn.bootx.paymentcenter.controller;

import cn.bootx.common.web.rest.Res;
import cn.bootx.common.web.rest.ResResult;
import cn.bootx.paymentcenter.core.paymodel.wallet.service.WalletService;
import cn.bootx.paymentcenter.dto.paymodel.wallet.WalletDto;
import cn.bootx.paymentcenter.param.paymodel.wallet.WalletActiveParam;
import cn.bootx.paymentcenter.param.paymodel.wallet.WalletProtectionSettingParam;
import cn.bootx.paymentcenter.param.paymodel.wallet.WalletRechargeParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * 钱包
 * @author xxm
 * @date 2021/2/24
 */
@Api(tags = "钱包相关的接口")
@RestController
@RequestMapping("wallet")
@AllArgsConstructor
public class WalletController {
    private final WalletService walletService;

    @ApiOperation("开通操作")
    @PostMapping("active")
    public ResResult<WalletDto> activeWallet(@ApiParam("开通钱包参数") @RequestBody WalletActiveParam param) {
        return Res.ok(walletService.activeWallet(param));
    }

    @ApiOperation("安全设置")
    @PostMapping("/protection/setting")
    public ResResult<WalletDto> enableProtection(@ApiParam("安全设置参数") @RequestBody WalletProtectionSettingParam param) {
        return Res.ok(walletService.setProtection(param));
    }

    @ApiOperation("充值操作")
    @PostMapping("recharge")
    public ResResult<Void> recharge(@ApiParam("充值参数") @RequestBody WalletRechargeParam param) {
        walletService.recharge(param);
        return Res.ok();
    }

    @ApiOperation("根据钱包ID查询钱包")
    @GetMapping("/getById")
    public ResResult<WalletDto> getWallet(@ApiParam(value = "钱包ID", required = true) @NotNull(message = "wallet id cannot be null") @RequestParam("walletId") Long walletId) {
        return Res.ok(walletService.getById(walletId));
    }

    @ApiOperation("根据用户ID查询钱包")
    @GetMapping("/getByUserId")
    public ResResult<WalletDto> getWalletByUserId(@ApiParam(value = "用户ID", required = true) @NotNull(message = "user id cannot be null") @RequestParam("userId") Long userId) {
        return Res.ok(walletService.getByUserId(userId));
    }

    @ApiOperation("根据钱包Token查询钱包")
    @GetMapping("/getByToken")
    public ResResult<WalletDto> getWalletByUserId(@ApiParam(value = "token", required = true) @NotNull(message = "token cannot be null") @RequestParam("token") String token) {
        return Res.ok(walletService.getByToken(token,false));
    }

    @ApiOperation("根据钱包Token查询钱包")
    @GetMapping("/getByTokenWithDelete")
    public ResResult<WalletDto> getWalletToken(@ApiParam(value = "token", required = true) @NotNull(message = "token cannot be null") @RequestParam("token") String token, @RequestParam("deleteToken") boolean deleteToken) {
        return Res.ok(walletService.getByToken(token, deleteToken));
    }

    @ApiOperation("生成用户钱包Token,5min有效期")
    @PostMapping("/token")
    public ResResult<String> getWalletToken(@ApiParam(value = "用户ID", required = true) @NotNull(message = "user id cannot be null") @RequestParam("userId") Long userId) {
        return Res.ok(walletService.generateWalletToken(userId));
    }

    @ApiOperation("删除Token,会返回删除的Token对应的walletId,token不存在会返回null")
    @GetMapping("getAndDeleteToken")
    public ResResult<Long> getAndDeleteWalletToken(@ApiParam(value = "token", required = true) @NotNull(message = "token cannot be null") @RequestParam("token") String token) {
        return Res.ok(walletService.getAndDeleteWalletId(token));
    }



}
