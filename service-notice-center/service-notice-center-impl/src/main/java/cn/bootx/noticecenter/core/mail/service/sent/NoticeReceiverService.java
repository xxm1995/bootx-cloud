package cn.bootx.noticecenter.core.mail.service.sent;


import cn.hutool.core.collection.CollectionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by sunyan on 2018/11/22.
 */
@Component
@RequiredArgsConstructor
public class NoticeReceiverService {

    /**
     * 处理接收人 测试环境
     * @param noticeType
     * @param originalReceivers
     * @return
     */
    public Set<String> handleReceivers(Integer noticeType, Set<String> originalReceivers){
        if(CollectionUtil.isEmpty(originalReceivers)){
            return originalReceivers;
        }
        return originalReceivers;
    }

}
