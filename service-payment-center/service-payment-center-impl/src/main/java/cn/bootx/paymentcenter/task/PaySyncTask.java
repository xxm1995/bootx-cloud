package cn.bootx.paymentcenter.task;

import cn.bootx.paymentcenter.core.pay.service.PaySyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**   
* 定时与支付网关进行同步
* @author xxm  
* @date 2021/4/21 
*/
@Component
@RequiredArgsConstructor
public class PaySyncTask {

    private final PaySyncService payRemoteSyncService;

    /**
     * 定时同步状态
     */
    @Scheduled(initialDelay = 10000,fixedDelay = 60*10000)
    public void syncPayStatus(){
//        payRemoteSyncService.syncPayStatus();
    }

}
