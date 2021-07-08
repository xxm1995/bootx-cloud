package cn.bootx.engine.shop.core.address.service;

import cn.bootx.common.web.exception.BizException;
import cn.bootx.engine.shop.core.address.dao.UserAddressManager;
import cn.bootx.engine.shop.core.address.dao.UserAddressRepository;
import cn.bootx.engine.shop.core.address.entity.UserAddress;
import cn.bootx.engine.shop.dto.address.UserAddressDto;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
* 用户收货地址
* @author xxm  
* @date 2021/2/1 
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class UserAddressService {
    private final UserAddressRepository userAddressRepository;
    private final UserAddressManager userAddressManager;

    private final Long userId = 1L;
    /**
     * 添加用户收货地址
     */
    @Transactional(rollbackFor = Exception.class)
    public UserAddressDto add(UserAddressDto param){
        UserAddress userAddress = UserAddress.init(param);
        userAddress.setUserId(userId);
        // 新增为默认地址清除默认
        if (userAddress.getIsDefault()){
            userAddressManager.clearDefault(userId);
        }
        UserAddress save = userAddressRepository.save(userAddress);
        return save.toDto();
    }


    /**
     * 获取默认收货地址
     */
    public UserAddressDto getDefault(){
        UserAddress userAddress = userAddressManager.findByDefault(userId)
                .orElseThrow(() -> new BizException("默认收货地址不存在"));

        return userAddress.toDto();
    }

    /**
     * 设置默认收货地址
     */
    @Transactional(rollbackFor = Exception.class)
    public void setUpDefault(Long id){
        // 清除默认
        userAddressManager.clearDefault(userId);
        // 设置默认
        userAddressManager.setUpDefault(id,userId);
    }

    /**
     * 获取地址
     */
    public UserAddressDto get(Long id){
        return userAddressManager.findById(id).map(UserAddress::toDto).orElse(null);
    }

    /**
     * 更新
     */
    public void edit(UserAddressDto param){
        UserAddress userAddress = userAddressManager.findById(param.getId())
                .orElseThrow(() -> new BizException("收货地址不存在"));
        BeanUtil.copyProperties(param,userAddress, CopyOptions.create().ignoreNullValue());
        userAddressRepository.save(userAddress);

    }

    /**
     * 删除
     */
    public void delete(Long id){
        userAddressManager.deleteById(id,userId);
    }


}
