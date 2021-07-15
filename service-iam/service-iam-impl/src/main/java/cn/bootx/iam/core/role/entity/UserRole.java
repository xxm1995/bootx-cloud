package cn.bootx.iam.core.role.entity;

import cn.bootx.starter.jpa.base.JpaTidEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
* @author xxm
* @date 2020/5/1 11:18
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Accessors(chain = true)
@Table(name = "uc_user_role")
@NoArgsConstructor
public class UserRole extends JpaTidEntity {

    /** 用户 */
    private Long userId;

    /** 角色 */
    private Long roleId;
}
