package cn.bootx.goods.mq;

import cn.bootx.goods.core.inventory.service.OperateInventoryService;
import cn.bootx.goods.param.inventory.ReduceInventoryParam;
import cn.bootx.goods.param.inventory.UnlockInventoryParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static cn.bootx.goods.event.GoodsEventCode.REDUCE_INVENTORY;
import static cn.bootx.goods.event.GoodsEventCode.UNLOCK_INVENTORY;

/**
 * 消息接收
 * @author xxm
 * @date 2021/4/22
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MessageListener {

    private final OperateInventoryService operateInventoryService;

    /**
     * 解锁库存事件
     */
    @RabbitListener(queues = UNLOCK_INVENTORY)
    public void unlockInventoryListener(UnlockInventoryParam param){
        log.info("解锁库存事件");
        operateInventoryService.unlockInventory(param.getSkuId(),param.getCount(),param.getToken());
    }

    /**
     * 扣减库存事件
     */
    @RabbitListener(queues = REDUCE_INVENTORY)
    public void reduceInventory(ReduceInventoryParam param){
        log.info("扣减库存事件");
        operateInventoryService.reduceInventory(param.getSkuId(),param.getCount(),param.getToken());
    }


}
