package cn.bootx.bsp.core.channel.entity;

import cn.bootx.bsp.core.channel.convert.ChannelConvert;
import cn.bootx.bsp.dto.channel.ChannelDto;
import cn.bootx.bsp.param.channel.ChannelParam;
import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.common.core.code.CommonCode;
import cn.bootx.starter.jpa.base.JpaBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
* 渠道表
* @author xxm
* @date 2020/10/16
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@Table(name = "bsp_channel")
@SQLDelete(sql = "update bsp_channel set deleted=" + CommonCode.DELETE_FLAG + " where id=?")
@Where(clause = "deleted=" + CommonCode.NORMAL_FLAG)
public class Channel extends JpaBaseEntity implements EntityBaseFunction<ChannelDto> {

    /**
     * 渠道名
     */
    private String name;

    /**
     * 渠道类型(0:自营 1:第三方 2:线上 3:线下)
     */
    private int type;

    /** key */
    @Column(name = "`key`")
    private String key;

    /** 是否启用 */
    @Column(name = "`active`")
    private Boolean active;

    /** 密钥 */
    private String secretKey;

    /** 渠道端id */
    private Long clientId;

    public static Channel init(ChannelDto dto){
        return ChannelConvert.CONVERT.convert(dto);
    }

    public static Channel init(ChannelParam param) {
        return ChannelConvert.CONVERT.convert(param);
    }

    @Override
    public ChannelDto toDto() {
        return ChannelConvert.CONVERT.convert(this);
    }
    
}
