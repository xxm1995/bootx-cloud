package cn.bootx.starter.auth.session.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* Token签名
* @author xxm  
* @date 2021/8/17 
*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TokenSign {
    /**
     * token值
     */
    private String value;

    /**
     * 所在设备标识
     */
    private String device;
}
