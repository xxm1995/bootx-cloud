package cn.bootx.noticecenter.core.dingtalk.robot.service;

import cn.bootx.common.web.exception.BizException;
import cn.bootx.noticecenter.core.dingtalk.robot.constant.DingRobotConst;
import cn.bootx.noticecenter.core.dingtalk.robot.dao.DingRobotConfigManage;
import cn.bootx.noticecenter.core.dingtalk.robot.entity.DingRobotConfig;
import cn.bootx.noticecenter.dto.dingtalk.DingTalkResult;
import cn.bootx.noticecenter.dto.dingtalk.notice.DingTalkNotice;
import cn.bootx.starter.jackson.utils.JacksonUtils;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
* 钉钉机器人消息发送
* @author xxm
* @date 2020/11/29
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class DingRobotSendService {
    private final DingRobotConfigManage dingRobotConfigManage;

    /**
     * 发送钉钉机器人消息
     */
    public DingTalkResult sendNotice(String code, DingTalkNotice body) {

        DingRobotConfig dingRobotConfig = dingRobotConfigManage.findByCode(code).orElseThrow(() -> new BizException("钉钉机器人配置不存在"));

        return this.sendNotice(dingRobotConfig,body);
    }

    /**
     * 发送文本消息
     */
    public DingTalkResult sendNotice(DingRobotConfig dingRobotConfig, DingTalkNotice body){
        long timestamp = System.currentTimeMillis();

        Map<String, Object> map = new HashMap<>(3);
        map.put(DingRobotConst.ACCESS_TOKEN, dingRobotConfig.getAccessToken());
        String url;
        // 验签
        if (dingRobotConfig.isEnableSignatureCheck()){
            url = DingRobotConst.SEND_SIGN_URL;
            map.put(DingRobotConst.SIGN, generateSign(timestamp, dingRobotConfig.getSignSecret()));
            map.put(DingRobotConst.TIMESTAMP, timestamp);
        }else {
            url = DingRobotConst.SEND_NOT_SIGN_URL;
        }

        // 请求消息
        String responseBody = HttpUtil.createPost(StrUtil.format(url, map))
                .body(JacksonUtils.toJson(body))
                .execute()
                .body();
        DingTalkResult dingTalkResult = JacksonUtils.toBean(responseBody, DingTalkResult.class);
        if (!Objects.equals(0,dingTalkResult.getCode())){
            log.error("钉钉机器人发送消息失败: {}",dingTalkResult.getMsg());
        }
        return dingTalkResult;
    }

    /**
     * 生成sign
     */
    private String generateSign(Long timestamp, String sec) {
        String combine = String.format("%d\n%s", timestamp, sec);
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(sec.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            byte[] signData = mac.doFinal(combine.getBytes(StandardCharsets.UTF_8));
            return Base64.encodeBase64String(signData);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }
}
