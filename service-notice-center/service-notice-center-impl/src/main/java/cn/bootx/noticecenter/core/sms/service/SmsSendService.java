package cn.bootx.noticecenter.core.sms.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
* 短信发送服务
* @author xxm
* @date 2020/6/5 21:07
*/
@Slf4j
@Service
@AllArgsConstructor
public class SmsSendService {

    /**
     * 短信发送
     */
//    @Async("asyncExecutor")
    public void sendSms(String phone,String msg){

    }
}
