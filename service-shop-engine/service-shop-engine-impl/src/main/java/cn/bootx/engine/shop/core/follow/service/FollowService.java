package cn.bootx.engine.shop.core.follow.service;

import cn.bootx.engine.shop.core.follow.dao.FollowManager;
import cn.bootx.engine.shop.core.follow.dao.FollowRepository;
import cn.bootx.engine.shop.core.follow.entity.Follow;
import cn.bootx.engine.shop.core.follow.factory.FollowFactory;
import cn.bootx.goods.client.GoodsClient;
import cn.bootx.goods.client.GoodsSkuClient;
import cn.bootx.goods.dto.goods.GoodsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**   
* 收藏关注
* @author xxm  
* @date 2021/2/18 
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowManager followManager;
    private final FollowRepository followRepository;

    private final GoodsSkuClient goodsSkuClient;
    private final GoodsClient goodsClient;

    private final FollowFactory followFactory;

    /**
     * 加入收藏
     */
    public void join(Long goodsId){
        GoodsDto goodsDto = goodsClient.getDetails(goodsId);
        Follow follow = followFactory.construct(goodsDto);
        followRepository.save(follow);
    }

    /**
     * 删除
     */
    public void delete(){
        
    }

    /**
     * 清除失效
     */
    public void clear(){
        // 查询失效的

        // 进行删除
    }
}
