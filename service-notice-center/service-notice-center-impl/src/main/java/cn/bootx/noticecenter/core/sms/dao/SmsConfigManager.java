package cn.bootx.noticecenter.core.sms.dao;

import cn.bootx.common.headerholder.HeaderHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
* @author xxm
* @date 2020/4/8 13:26
*/
@Slf4j
@Repository
@RequiredArgsConstructor
public class SmsConfigManager{

    private final SmsConfigRepository smsConfigRepository;

    private final HeaderHolder headerHolder;

}
