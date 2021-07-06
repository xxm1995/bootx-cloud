package cn.bootx.noticecenter.core.dingtalk.robot.constant;

/**   
* 钉钉机器人常量类
* @author xxm  
* @date 2020/11/29 
*/
public class DingRobotConst {
    /** 钉钉机器人基本url */
    public final static String BASE_URL = "https://oapi.dingtalk.com/robot/";
    /** 带sign的消息发送请求 */
    public final static String SEND_SIGN_URL = BASE_URL+"send?access_token={accessToken}&timestamp={timestamp}&sign={sign}";
    /** 不带sign的消息发送请求 */
    public final static String SEND_NOT_SIGN_URL = BASE_URL+"send?access_token={accessToken}";

    /** 钉钉配置 */
    public final static String ACCESS_TOKEN = "accessToken";
    public final static String SIGN = "sign";
    public final static String TIMESTAMP = "timestamp";
}
