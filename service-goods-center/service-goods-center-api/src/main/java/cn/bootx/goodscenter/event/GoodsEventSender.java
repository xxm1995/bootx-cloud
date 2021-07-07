package cn.bootx.goodscenter.event;

import cn.bootx.goodscenter.param.inventory.ReduceInventoryParam;
import cn.bootx.goodscenter.param.inventory.UnlockInventoryParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * 商品中心发送
 * @author xxm
 * @date 2021/4/22
 */
@Slf4j
@RequiredArgsConstructor
public class GoodsEventSender {
    private final RabbitTemplate rabbitTemplate;

    /**
     * 发送解锁库存事件
     */
    public void sendUnlockInventory(UnlockInventoryParam param){
        rabbitTemplate.convertAndSend(
                GoodsEventCode.EXCHANGE_GOODS,
                GoodsEventCode.UNLOCK_INVENTORY,
                param
        );
    }

    /**
     * 发送解锁库存事件
     */
    public void sendReduceInventory(ReduceInventoryParam param){
        rabbitTemplate.convertAndSend(
                GoodsEventCode.EXCHANGE_GOODS,
                GoodsEventCode.REDUCE_INVENTORY,
                param
        );
    }

}
