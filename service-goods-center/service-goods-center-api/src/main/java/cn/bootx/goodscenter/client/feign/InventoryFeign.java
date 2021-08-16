package cn.bootx.goodscenter.client.feign;

import cn.bootx.common.core.rest.ResResult;
import cn.bootx.goodscenter.code.GoodsCenterCode;
import cn.bootx.goodscenter.dto.inventory.LockInventoryDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = GoodsCenterCode.APPLICATION_NAME,contextId = "inventoryFeign",path = "inventory")
public interface InventoryFeign {

    @ApiOperation("获取指定 SKU 的可用库存")
    @GetMapping("/getAvailable")
    ResResult<Integer> getAvailable(@RequestParam Long skuId);

    @ApiOperation("锁定库存")
    @PostMapping("/lockInventory")
    ResResult<LockInventoryDto> lockInventory(@RequestParam Long skuId,@RequestParam int count);

    @ApiOperation("释放SKU库存，需使用token")
    @PostMapping("/unlockInventory")
    ResResult<Void> unlockInventory(@RequestParam Long skuId,@RequestParam int count,@RequestParam String token);

    @ApiOperation("解锁没有令牌的库存")
    @PostMapping("/unlockInventoryWithoutToken")
    ResResult<Void> unlockInventoryWithoutToken(@RequestParam Long skuId,@RequestParam int count);

    @ApiOperation("扣减指定 SKU 的预占库存，增加对应售出")
    @PostMapping("/reduceInventory")
    ResResult<Void> reduceInventory(@RequestParam Long skuId,@RequestParam int count,@RequestParam String token);

    @ApiOperation("增补指定 SKU 的可售库存， 扣减对应售出")
    @PostMapping("/increaseInventory")
    ResResult<Void> increaseInventory(@RequestParam Long skuId, @RequestParam int count);

    @ApiOperation("增补指定 SKU 的可售库存")
    @PostMapping("/increaseAvailable")
    ResResult<Void> increaseAvailable(@RequestParam Long skuId, @RequestParam int count);

    @ApiOperation("扣减指定的 SKU 的库存")
    @PostMapping("/reduceLockedAndCapacity")
    ResResult<Void> reduceLockedAndCapacity(@RequestParam Long skuId, @RequestParam int count, @RequestParam String token);

    @ApiOperation("扣减指定 SKU 的售出库存并减少总库存")
    @PostMapping("/reduceSoldAndCapacity")
    ResResult<Void> reduceSoldAndCapacity(@RequestParam Long skuId, @RequestParam int count);
}
