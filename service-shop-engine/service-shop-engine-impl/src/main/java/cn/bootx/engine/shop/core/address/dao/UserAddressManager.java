package cn.bootx.engine.shop.core.address.dao;

import cn.bootx.engine.shop.core.address.entity.UserAddress;
import cn.bootx.starter.headerholder.HeaderHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
* 用户地址
* @author xxm
* @date 2021/2/17
*/
@Repository
@RequiredArgsConstructor
public class UserAddressManager {
    private final UserAddressRepository userAddressRepository;

    private final HeaderHolder headerHolder;

    public Optional<UserAddress> findById(Long id){
        return userAddressRepository.findByIdAndTid(id,headerHolder.findTid());
    }

    /**
     * 获取默认
     * @param userId
     */
    public Optional<UserAddress> findByDefault(Long userId) {
        return userAddressRepository.findByUserIdAndIsDefaultAndTid(userId,true,headerHolder.findTid());
    }

    /**
     * 清除默认
     */
    public void clearDefault(Long userId) {
        userAddressRepository.clearDefault(userId,headerHolder.findTid());
    }

    /**
     * 设置默认
     */
    public void setUpDefault(Long id,Long userId) {
        userAddressRepository.setUpDefault(id,userId,headerHolder.findTid());

    }

    /**
     * 删除
     */
    public void deleteById(Long id,Long userId) {
        userAddressRepository.deleteByIdAndUserIdAndTid(id,userId,headerHolder.findTid());
    }
}
