package cn.bootx.usercenter.core.user.service;

import cn.bootx.usercenter.core.user.entity.UserInfo;
import cn.bootx.usercenter.core.user.dao.UserInfoManager;
import cn.bootx.usercenter.core.user.dao.UserInfoRepository;
import cn.bootx.usercenter.dto.user.UserInfoDto;
import cn.bootx.usercenter.exception.user.UserInfoNotExistsException;
import cn.bootx.usercenter.exception.user.UserNonePhoneAndEmailException;
import cn.bootx.usercenter.param.user.UserInfoParam;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

/**
* 用户
* @author xxm
* @date 2020/4/27 21:11
*/
@Service
@RequiredArgsConstructor
public class UserInfoService {

    private final UserInfoManager userInfoManager;
    private final UserInfoRepository userInfoRepository;

    /**
     * 根据用户id 获取 UserInfo
     */
    public UserInfoDto getById(Long id){
        return userInfoManager.findById(id).map(UserInfo::toDto).orElse(null);
    }

    /**
     * 邮箱是否存在
     */
    public boolean existsEmail(String email) {
        if (StrUtil.isBlank(email)) {
            return false;
        }
        return userInfoManager.existsEmail(email.trim());
    }

    /**
     * 手机是否存在
     */
    public boolean existsPhone(String phone) {
        if (StrUtil.isBlank(phone)) {
            return false;
        }
        return userInfoManager.existsPhone(phone);
    }

    /**
     * 根据邮箱查询用户
     */
    public UserInfoDto getByEmail(String email) {
        return userInfoManager.findByEmail(email).map(UserInfo::toDto).orElse(null);
    }

    /**
     * 根据手机号查询用户
     */
    public UserInfoDto getByPhone(String phone) {
        return userInfoManager.findByPhone(phone).map(UserInfo::toDto).orElse(null);
    }

    /**
     * 账户是否存在
     */
    public boolean existsById(Long id) {
        if (Objects.isNull(id)) {
            return false;
        }
        return userInfoManager.existsById(id);
    }

    /**
     * 注册新用户 返回添加后用户信息, 已经存在则直接返回
     */
    public UserInfoDto addUserInfo(UserInfoParam userInfoParam){

        // 如果用户的手机号和邮箱都不存在则抛出异常, 第三方登录除外
        if (Objects.isNull(userInfoParam.getPhone()) && Objects.isNull(userInfoParam.getEmail())
                && !userInfoParam.getThirdPartyLogin()) {
            throw new UserNonePhoneAndEmailException();
        }

        // 用户已存在则直接返回
        UserInfo userInfoNew;
        if (this.existsEmail(userInfoParam.getEmail())) {
            //noinspection OptionalGetWithoutIsPresent
            userInfoNew = userInfoManager.findByEmail(userInfoParam.getEmail()).get();
        } else if (existsPhone(userInfoParam.getPhone())) {
            //noinspection OptionalGetWithoutIsPresent
            userInfoNew = userInfoManager.findByPhone(userInfoParam.getPhone()).get();
        } else {
            // 注册时间
            UserInfo userInfo = UserInfo.init(userInfoParam);
            userInfo.setRegisterTime(LocalDateTime.now());
            userInfoNew = userInfoRepository.save(userInfo);
        }
        return userInfoNew.toDto();
    }

    /**
     * 编辑用户信息
     */
    public UserInfoDto editUserInfo(UserInfoDto userInfoDto){
        UserInfo userInfo = userInfoManager.findById(userInfoDto.getId()).orElseThrow(UserInfoNotExistsException::new);
        BeanUtil.copyProperties(userInfoDto,userInfo, CopyOptions.create().ignoreNullValue());
        return userInfoRepository.save(userInfo).toDto();
    }

}
