package cn.bootx.noticecenter.dto.dingtalk.notice;

import cn.bootx.noticecenter.code.DingTalkNoticeType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author xxm
* @date 2020/11/30
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@ApiModel("钉钉")
@NoArgsConstructor
public class DingTalkLinkNotice extends DingTalkNotice implements Serializable {
    private static final long serialVersionUID = -3094638065840434973L;

    @ApiModelProperty("钉钉link消息体")
    private DingTalkLink link;

    public DingTalkLinkNotice(String title,String msg,String messageUrl) {
        super(DingTalkNoticeType.LINK);
        link = new DingTalkLink(title,msg,messageUrl);
    }
    public DingTalkLinkNotice(String title,String msg,String messageUrl,String picUrl){
        super(DingTalkNoticeType.LINK);
        link = new DingTalkLink(title,msg,messageUrl,picUrl);
    }
}
