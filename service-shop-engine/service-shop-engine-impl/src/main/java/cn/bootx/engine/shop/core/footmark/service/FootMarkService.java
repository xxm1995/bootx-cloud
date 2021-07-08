package cn.bootx.engine.shop.core.footmark.service;

import cn.bootx.common.web.util.ResultConvertUtils;
import cn.bootx.engine.shop.core.footmark.dao.FootMarkManager;
import cn.bootx.engine.shop.dto.footmark.FootMarkDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 访问足迹
* @author xxm
* @date 2021/2/22
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class FootMarkService {
    private final FootMarkManager footMarkManager;

    /**
     * 添加足迹(事件驱动)
     */
    @Async("asyncExecutor")
//    @EventListener
    public void add(){
        // 判断商品是否已经访问过, 访问过更新访问日期

        // 没访问过添加访问记录
    }

    /**
    * 获取用户的足迹 (需要分页的)
    */
    public List<FootMarkDto> findAll(){
        Long userId = 0L;
        return ResultConvertUtils.dtoListConvert(footMarkManager.findByUser(userId));
    }

}
