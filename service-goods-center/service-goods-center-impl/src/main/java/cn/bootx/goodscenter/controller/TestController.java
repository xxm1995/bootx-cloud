package cn.bootx.goodscenter.controller;

import cn.bootx.common.core.exception.BizException;
import cn.bootx.common.core.rest.Res;
import cn.bootx.common.core.rest.ResResult;
import cn.bootx.goodscenter.core.inventory.service.OperateInventoryService;
import cn.bootx.starter.seata.redis.TccRedisClient;
import io.seata.spring.annotation.GlobalTransactional;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**   
* 测试处理器
* @author xxm  
* @date 2021/4/27 
*/
@Api(tags = "测试处理器")
@RestController
@RequestMapping("/test")
@AllArgsConstructor
public class TestController {
    private final OperateInventoryService operateInventoryService;
    private final TccRedisClient tccRedisClient;

    @ApiOperation("分布式事务(关闭)")
    @PostMapping("/tcc-off")
    public ResResult<Void> tcc(){
        operateInventoryService.lockInventory(1L,3);
        if (true){
            throw new BizException("异常");
        }
        return Res.ok();
    }

    @ApiOperation("分布式事务(开启)")
    @GetMapping("/tcc-on")
    @GlobalTransactional(rollbackFor = Exception.class)
    public ResResult<Void> redis(){
        operateInventoryService.lockInventory(1L,3);
        if (true){
            throw new BizException("异常");
        }
        return Res.ok();
    }
}
