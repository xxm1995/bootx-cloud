package cn.bootx.engine.shop.core.cart.dao;

import cn.bootx.engine.shop.core.cart.entity.ShopCart;
import cn.bootx.starter.headerholder.HeaderHolder;
import cn.bootx.starter.redis.RedisClient;
import cn.hutool.json.JSONUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;

/**   
* 购物车存储操作
* @author xxm  
* @date 2021/1/4 
*/
@Repository
@RequiredArgsConstructor
public class CartManager {
    private final RedisClient redisClient;
    private final HeaderHolder headerHolder;
    private final String CART = "cart:";

    /**
     * 购物车是否存在
     */
    public boolean exists(Long userId){
        String key = this.key(userId);
        return redisClient.exists(key);
    }

    public ShopCart get(Long userId){
        if (this.exists(userId)){
            String json = redisClient.get(this.key(userId));
            return JSONUtil.toBean(json, ShopCart.class);
        }
        return new ShopCart()
                .setUserId(userId)
                .setCartItems(new LinkedHashMap<>());
    }

    public void save(ShopCart shopCart){
        Long userId = shopCart.getUserId();
        redisClient.set(this.key(userId),JSONUtil.toJsonStr(shopCart));
    }

    private String key(Long value) {
        return this.key(String.valueOf(value));
    }

    private String key(String value){
        String cartPrefix = CART + headerHolder.findTid();
        return cartPrefix + ":" + value;
    }
}
