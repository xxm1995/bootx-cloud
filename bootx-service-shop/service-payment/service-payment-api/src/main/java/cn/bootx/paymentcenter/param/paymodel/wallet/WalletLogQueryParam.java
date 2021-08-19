package cn.bootx.paymentcenter.param.paymodel.wallet;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author xxm
* @date 2020/12/8
*/
@Data
@Accessors(chain = true)
@ApiModel("钱包日志查询参数")
public class WalletLogQueryParam implements Serializable {

    private static final long serialVersionUID = -4046664021959786637L;
    @ApiModelProperty("钱包ID (与userId至少存在一个)")
    private Long walletId;

    @ApiModelProperty("用户ID (钱包至少存在一个)")
    private Long userId;

    @ApiModelProperty("开始日期")
    private LocalDateTime startDate;

    @ApiModelProperty("结束日期")
    private LocalDateTime endDate;

    @ApiModelProperty("日志类型，不传则查询全部")
    private List<Integer> type;

}
