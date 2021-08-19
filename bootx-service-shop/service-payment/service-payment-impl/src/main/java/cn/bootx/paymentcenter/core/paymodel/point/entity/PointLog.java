package cn.bootx.paymentcenter.core.paymodel.point.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.paymentcenter.dto.point.PointLogDto;
import cn.bootx.common.jpa.base.JpaBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 积分日吹
* @author xxm
* @date 2020/12/11
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@Table(name = "pc_point_log")
public class PointLog extends JpaBaseEntity implements EntityBaseFunction<PointLogDto> {

    /** 用户id */
    private Long userId;

    /** 积分 */
    private int points;

    /** 描述 */
    private String description;

    /** 业务id */
    private String businessId;

    /** 类型名称 */
    private String typeName;

    @Override
    public PointLogDto toDto(){
        PointLogDto dto = new PointLogDto();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }

}
