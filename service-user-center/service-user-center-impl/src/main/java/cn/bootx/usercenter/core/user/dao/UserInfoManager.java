package cn.bootx.usercenter.core.user.dao;

import cn.bootx.starter.headerholder.HeaderHolder;
import cn.bootx.usercenter.core.user.entity.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**   
* 用户信息
* @author xxm  
* @date 2020/4/24 15:32
*/
@Repository
@RequiredArgsConstructor
public class UserInfoManager {

    private final UserInfoRepository userInfoRepository;
    private final HeaderHolder headerHolder;

    public Optional<UserInfo> findById(Long id) {
        return userInfoRepository.findByIdAndTid(id,headerHolder.findTid());
    }

    public boolean existsEmail(String email) {
        return userInfoRepository.existsByEmailAndTid(email,headerHolder.findTid());
    }

    public boolean existsPhone(String phone) {
        return userInfoRepository.existsByEmailAndTid(phone,headerHolder.findTid());
    }

    public Optional<UserInfo> findByEmail(String email) {
        return userInfoRepository.findByEmailAndTid(email,headerHolder.findTid());
    }

    public Optional<UserInfo> findByPhone(String phone) {
        return userInfoRepository.findByPhoneAndTid(phone,headerHolder.findTid());
    }

    public boolean existsById(Long id) {
        return userInfoRepository.existsByIdAndTid(id,headerHolder.findTid());
    }

    public List<UserInfo> findAllByIds(List<Long> ids) {
        return userInfoRepository.findByIdInAndTid(ids,headerHolder.findTid());
    }
}
