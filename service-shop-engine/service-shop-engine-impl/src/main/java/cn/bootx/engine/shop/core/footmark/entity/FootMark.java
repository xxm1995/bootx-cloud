package cn.bootx.engine.shop.core.footmark.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.engine.shop.dto.footmark.FootMarkDto;
import cn.bootx.common.jpa.base.JpaTidEntity;
import cn.hutool.core.bean.BeanUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**   
* 足迹
* @author xxm  
* @date 2021/2/22 
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@Table(name = "se_foot_mark")
public class FootMark extends JpaTidEntity implements EntityBaseFunction<FootMarkDto> {
    /** 用户id */
    private Long userId;

    /** 商品id */
    private Long goodsId;

    /** 商品名称 */
    private String goodsName;

    /** 商品价格 */
    private BigDecimal displayPrice;

    /** 商品介绍主图 */
    private String mainUri;

    /** 时间 */
    private LocalDateTime lookTime;

    @Override
    public FootMarkDto toDto() {
        FootMarkDto dto = new FootMarkDto();
        BeanUtil.copyProperties(this,dto);
        return dto;
    }
}
