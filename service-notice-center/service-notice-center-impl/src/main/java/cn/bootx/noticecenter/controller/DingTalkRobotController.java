package cn.bootx.noticecenter.controller;

import cn.bootx.common.core.rest.Res;
import cn.bootx.common.core.rest.ResResult;
import cn.bootx.noticecenter.dto.dingtalk.DingTalkResult;
import cn.bootx.noticecenter.dto.dingtalk.notice.DingTalkLinkNotice;
import cn.bootx.noticecenter.dto.dingtalk.notice.DingTalkMarkdownNotice;
import cn.bootx.noticecenter.dto.dingtalk.notice.DingTalkTextNotice;
import cn.bootx.noticecenter.core.dingtalk.robot.service.DingRobotConfigService;
import cn.bootx.noticecenter.core.dingtalk.robot.service.DingRobotSendService;
import cn.bootx.noticecenter.dto.dingtalk.DingRobotConfigDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * @author xxm
 * @date 2020/11/29
 */
@Api(tags = "钉钉机器人")
@RestController
@RequestMapping("/ding/robot")
@AllArgsConstructor
public class DingTalkRobotController {
    private final DingRobotSendService dingRobotSendService;
    private final DingRobotConfigService dingRobotConfigService;

    @ApiOperation("新增机器人配置")
    @PostMapping("/add")
    public ResResult<DingRobotConfigDto> add(DingRobotConfigDto configDto){
        return Res.ok(dingRobotConfigService.addConfig(configDto));
    }

    @ApiOperation("发送text")
    @GetMapping("/send/text")
    public ResResult<DingTalkResult> sendText(){
        DingTalkTextNotice notice = new DingTalkTextNotice("hello dingTalk!",Collections.singletonList("17051008321"));
        return Res.ok(dingRobotSendService.sendNotice("bootx",notice));
    }

    @ApiOperation("发送link")
    @GetMapping("/send/link")
    public ResResult<DingTalkResult> sendLink(){
        DingTalkLinkNotice notice = new DingTalkLinkNotice("时代的火车向前开", "这个即将发布的新版本，创始人xx称它为红树林", "https://www.dingtalk.com/s?__biz=MzA4NjMwMTA2Ng==&mid=2650316842&idx=1&sn=60da3ea2b29f1dcc43a7c8e4a7c97a16&scene=2&srcid=09189AnRJEdIiWVaKltFzNTw&from=timeline&isappinstalled=0&key=&ascene=2&uin=&devicetype=android-23&version=26031933&nettype=WIFI");

        return Res.ok(dingRobotSendService.sendNotice("bootx",notice));
    }

    @ApiOperation("发送md")
    @GetMapping("/send/md")
    public ResResult<DingTalkResult> sendMd(){
        DingTalkMarkdownNotice notice = new DingTalkMarkdownNotice("杭州天气","#### 杭州天气\n > 9度，西北风1级，空气良89，相对温度73%\n > ![screenshot](https://img.alicdn.com/tfs/TB1NwmBEL9TBuNjy1zbXXXpepXa-2400-1218.png)\n > ###### 10点20分发布 [天气](https://www.dingtalk.com) \n"
                ,Collections.singletonList("17051008321"));
        return Res.ok(dingRobotSendService.sendNotice("bootx",notice));
    }
}
