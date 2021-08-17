package cn.bootx.engine.shop.core.memberlevel.entity;

import cn.bootx.common.jpa.base.JpaBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
* 会员等级配置
* @author xxm  
* @date 2021/2/22 
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@Table(name = "sc_member_level_config")
public class MemberLevelConfig extends JpaBaseEntity {
}
