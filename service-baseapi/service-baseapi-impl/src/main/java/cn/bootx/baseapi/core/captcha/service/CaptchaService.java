package cn.bootx.baseapi.core.captcha.service;

import cn.bootx.baseapi.core.captcha.dao.CaptchaRepository;
import cn.bootx.baseapi.dto.captcha.CaptchaDataResult;
import cn.bootx.bsp.client.TenantClient;
import cn.bootx.bsp.dto.tenant.TenantDto;
import cn.hutool.core.util.RandomUtil;
import com.wf.captcha.ArithmeticCaptcha;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 验证码
 * @author xxm
 * @date 2020/5/8 17:58
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CaptchaService {

    private final CaptchaRepository captchaRepository;
    private final TenantClient tenantClient;
    /**
     * 生成算数验证码
     */
    public CaptchaDataResult generateArithmetic(String key){

        // 几位数运算，默认是两位
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(105,35);
        // 获取运算的结果
        String text = captcha.text();

        log.info("验证码: {}",text);

        // 写入redis中
        captchaRepository.saveToRedisWithExpireTime(key,text);

        return new CaptchaDataResult()
                .setCode(key)
                .setData(captcha.toBase64());
    }

    /**
     * 生成算数验证码
     */
    public CaptchaDataResult generateArithmetic(){
        log.info("123");
        List<TenantDto> all = tenantClient.findAll();
        System.out.println(all);
        String randomStr = RandomUtil.randomString(10);
        return this.generateArithmetic(randomStr);
    }

    /**
     * 判断验证码是否正确
     */
    public boolean validation(String key,String value){
        return captchaRepository.validationAndRemove(key,value);
    }
}
