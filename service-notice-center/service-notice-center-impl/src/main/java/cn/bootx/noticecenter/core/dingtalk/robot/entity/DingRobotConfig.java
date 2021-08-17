package cn.bootx.noticecenter.core.dingtalk.robot.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.noticecenter.dto.dingtalk.DingRobotConfigDto;
import cn.bootx.common.jpa.base.JpaBaseEntity;
import cn.hutool.core.bean.BeanUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;

/**   
* 钉钉机器人配置
* @author xxm  
* @date 2020/11/29 
*/
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "nc_ding_robot_config")
@Data
@Accessors(chain = true)
public class DingRobotConfig extends JpaBaseEntity implements EntityBaseFunction<DingRobotConfigDto> {

    /** 机器人配置名称 */
    private String name;

    /** 编号 */
    private String code;

    /** 钉钉机器人的accessToken */
    private String accessToken;

	/** 是否开启验签 */
	private boolean enableSignatureCheck;

	/** 验签秘钥 */
	private String signSecret;

    public static DingRobotConfig init(DingRobotConfigDto dto){
        DingRobotConfig entity = new DingRobotConfig();
        BeanUtil.copyProperties(dto,entity);
        return entity;
    }

    @Override
    public DingRobotConfigDto toDto() {
        DingRobotConfigDto dto = new DingRobotConfigDto();
        BeanUtil.copyProperties(this,dto);
        return dto;
    }
}
