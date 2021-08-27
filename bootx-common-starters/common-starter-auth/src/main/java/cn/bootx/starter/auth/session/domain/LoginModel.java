package cn.bootx.starter.auth.session.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
* 登录模式
* @author xxm
* @date 2021/8/17
*/
@Getter
@Setter
@Accessors(chain = true)
public class LoginModel {

    /**
     * 此次登录的客户端设备标识
     */
    private String device;

    /**
     * 指定此次登录token的有效期, 单位:秒 （如未指定，自动取全局配置的timeout值）
     */
    private long timeout;
}
