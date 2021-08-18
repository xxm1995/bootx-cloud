package cn.bootx.notice.client;


import cn.bootx.notice.dto.dingtalk.DingTalkResult;
import cn.bootx.notice.dto.dingtalk.notice.DingTalkNotice;

/**   
* 钉钉机器人
* @author xxm  
* @date 2020/12/1 
*/
public interface DingRobotSendClient {
    /**
     * 发送钉钉机器人消息
     */
    DingTalkResult sendNotice(String code, DingTalkNotice body);
}
