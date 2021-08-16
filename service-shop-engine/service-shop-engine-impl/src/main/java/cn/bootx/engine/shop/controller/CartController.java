package cn.bootx.engine.shop.controller;

import cn.bootx.common.core.rest.Res;
import cn.bootx.common.core.rest.ResResult;
import cn.bootx.engine.shop.core.cart.entity.ShopCart;
import cn.bootx.engine.shop.core.cart.service.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xxm
 * @date 2021/2/15
 */
@Api(tags = "购物车")
@RestController
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {
    private final CartService cartService;

    @ApiOperation("查看")
    @GetMapping("/get")
    public ResResult<ShopCart> get(){
        return Res.ok(cartService.getWhole());
    }

    @ApiOperation("添加")
    @PostMapping("/add")
    public ResResult<Void> add(Long skuId){
        cartService.add(skuId);
        return Res.ok();
    }

    @ApiOperation("更改数量")
    @PostMapping("/changeCount")
    public ResResult<Void> changeCount(Long skuId,int num){
        cartService.changeCount(skuId,num);
        return Res.ok();
    }

    @ApiOperation("更改规格")
    @PostMapping("/changeSpecifications")
    public ResResult<Void> changeSpecifications(Long oldSkuId,Long newSkuId){
        cartService.changeSpecifications(oldSkuId,newSkuId);
        return Res.ok();
    }

    @ApiOperation("选择优惠")
    @PostMapping("/selectActivity")
    public ResResult<Void> selectActivity(Long skuId,Long activityId){
        cartService.selectActivity(skuId,activityId);
        return Res.ok();
    }

    @ApiOperation("选中或取消选中")
    @PostMapping("/select")
    public ResResult<Void> select(Long skuId,int type){
        cartService.select(skuId,type);
        return Res.ok();
    }

    @ApiOperation("删除")
    @PostMapping("/remove")
    public ResResult<Void> remove(Long skuId){
        cartService.remove(skuId);
        return Res.ok();
    }
}
