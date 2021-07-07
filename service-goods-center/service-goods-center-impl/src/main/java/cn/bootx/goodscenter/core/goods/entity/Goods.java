package cn.bootx.goodscenter.core.goods.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.goodscenter.dto.goods.GoodsDto;
import cn.bootx.starter.jpa.base.JpaBaseEntity;
import cn.hutool.core.bean.BeanUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**   
* 商品表
* @author xxm  
* @date 2020/11/19 
*/
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Accessors(chain = true)
@Table(name = "gc_goods")
public class Goods extends JpaBaseEntity implements EntityBaseFunction<GoodsDto> {

    /** 所属类目id */
    private Long cid;

    /** 所属类目名称 */
    private String cname;

    /** 所属商店id */
    private Long shopId;

    /** 外部编码 */
    private String outNo;

    /** 商品code */
    private String code;

    /** 商品名称 */
    private String name;

    /** 描述*/
    private String description;

    /** banner图片 多个图片逗号分隔 */
    private String bannerUri;

    /** 商品介绍主图 多个图片逗号分隔 */
    private String mainUri;

    /** 显示上限价格 */
    private BigDecimal displayUpperPrice;

    /** 显示下限价格 */
    private BigDecimal displayLowerPrice;

    /** 是否打包品 */
    private boolean packing;

    /** 商品类型(虚拟/实体/赠品/加价购/服务) */
    private Integer goodsType;

    /** 0下架 1上架 2违规平台下架 */
    private Integer saleState;

    /** 上架时间 */
    private LocalDateTime saleOnTime;
    /** 下架时间 */
    private LocalDateTime saleOffTime;

    /** 商品的所有属性定义 id 拼接串 */
    private String attrDefIds;

    /** 品的所有属性值拼接串 */
    private String attrValues;

    /** 品的所有属性显示值拼接串 */
    @Transient
    private String attrValueDisplays;

    /** 附加信息 */
    private String addition;

    /** 状态(1:可用，0:不可用) */
    private int state;

    public static Goods init(GoodsDto dto){
        Goods entity = new Goods();
        BeanUtil.copyProperties(dto,entity);
        return entity;
    }

    @Override
    public GoodsDto toDto() {
        GoodsDto dto = new GoodsDto();
        BeanUtil.copyProperties(this,dto);
        return dto;
    }
}
