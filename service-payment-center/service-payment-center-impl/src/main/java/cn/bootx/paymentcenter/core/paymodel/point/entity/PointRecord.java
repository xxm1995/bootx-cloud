package cn.bootx.paymentcenter.core.paymodel.point.entity;

import cn.bootx.common.jpa.base.JpaBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**   
* 积分记录
* @author xxm  
* @date 2020/12/11 
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@Table(name = "pc_point_record")
public class PointRecord extends JpaBaseEntity {

    /** 用户id */
    private Long userId;

    /** 类型 */
    private int type;

    /** 原始积分 */
    private int originPoints;

    /** 当前积分 */
    private int currentPoints;

    /** 到期时间 */
    private LocalDateTime expireDate;

    /** 业务id */
    private String businessId;
}
