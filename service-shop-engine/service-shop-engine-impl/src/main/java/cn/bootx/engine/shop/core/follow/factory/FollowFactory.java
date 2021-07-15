package cn.bootx.engine.shop.core.follow.factory;

import cn.bootx.engine.shop.core.follow.entity.Follow;
import cn.bootx.goodscenter.dto.goods.GoodsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author xxm
 * @date 2021/2/18
 */
@Component
@RequiredArgsConstructor
public class FollowFactory {


    /**
     * 构建
     */
    public Follow construct(GoodsDto goodsDto){
        Follow follow = new Follow();

        follow.setName(goodsDto.getName())
                .setCategoryId(goodsDto.getCid())
                .setType(1)
                .setGoodsId(goodsDto.getId());

        return follow;
    }
}
