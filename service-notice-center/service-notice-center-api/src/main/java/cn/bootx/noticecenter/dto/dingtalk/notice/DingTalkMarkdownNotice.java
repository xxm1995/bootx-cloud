package cn.bootx.noticecenter.dto.dingtalk.notice;

import cn.bootx.noticecenter.code.DingTalkNoticeType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**   
* 钉钉markdown消息
* @author xxm  
* @date 2020/11/30 
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@NoArgsConstructor
@ApiModel("钉钉文本消息")
public class DingTalkMarkdownNotice extends DingTalkNotice implements Serializable {

    private static final long serialVersionUID = -2724590259000709240L;
    @ApiModelProperty("markdown消息体")
    private DingTalkMarkdown markdown;

	public DingTalkMarkdownNotice(String title, String msg, List<String> phones) {
		super(new DingTalkAt(phones), DingTalkNoticeType.MARKDOWN);
        markdown = new DingTalkMarkdown(title, msg + super.getAt().toAt());
	}

	public DingTalkMarkdownNotice(String title, String msg){
        super(DingTalkNoticeType.MARKDOWN);
        markdown = new DingTalkMarkdown(title, msg);
    }

}
