# Bootx-Cloud
<p>
 <img src='https://gitee.com/bootx/bootx-cloud/badge/star.svg?theme=dark' alt='star'/>
 <img src="https://img.shields.io/badge/Boot%20Cloud-2.0.0-success.svg" alt="Build Status">
 <img src="https://img.shields.io/badge/Author-Bootx-orange.svg" alt="Build Status">
 <img src="https://img.shields.io/badge/Spring%20Boot-2.5-blue.svg" alt="Downloads">
 <img src="https://img.shields.io/badge/Spring%20Cloud-2020-blue.svg" alt="Coverage Status">
 <img src="https://img.shields.io/badge/license-Apache%20License%202.0-green.svg"/>
</p>

## 🍈项目介绍

​		Spring Cloud分布式敏捷开发系统架构，提供整套公共微服务服务模块，努力为打造全方位企业级开发解决方案，
致力将开源版打造成超越商业版后台管理框架的项目，**对应单体版实现 [Bootx-Platform](https://gitee.com/bootx/bootx-platform)**

服务分布：

1. 基础服务：统一网关、IAM认证、日志分析、消息通知等
2. 平台服务：平台管理、业务报警平台等
3. 电商服务：收单支付、营销管理、商品管理、订单管理等
4. 数据服务：日志分析、数据中心(开发中)等
5. OA办公服务：工作流服务(开发中)、办公中心(开发中)

## 🥥项目体验

- 系统管理平台：[管理平台](http://web.cloud.bootx.cn/)
- Swagger聚合接口：[API文档](http://gateway.dev.bootx.cn:9000/doc.html)
- 日志分析：[Kibana管理平台](http://elk.dev.bootx.cn:5601/app/discover#)

## 🍒文档

- 前端项目地址：[https://gitee.com/bootx/bootx-cloud-ui](https://gitee.com/bootx/bootx-cloud-ui)
- 项目文档：[开发文档](https://www.yuque.com/bootx/bootx-cloud/)
- 项目启动：[启动文档](https://www.yuque.com/bootx/bootx-cloud/vpi0gn)
- 开发计划：[开发计划](https://www.yuque.com/bootx/bootx-cloud/xzmc6c)

## 🚩特色功能演示

### 简单支付演示
> 生成聚合支付码、使用微信和支付扫码时会自动使用相对应的支付方式，支持主动扫码和被动扫码
>
> [简单支付演示地址](http://web.cloud.bootx.cn/?#/cashier)
> 
> 注: 简单支付UI设计参考过[jeepay支付演示界面](https://www.jeequan.com/demo/jeepay_cashier.html)，因为与[jeepay](https://gitee.com/jeequan/jeepay)项目许可证冲突，所以没有参考和引用代码，在此感谢[jeepay](https://gitee.com/jeequan/jeepay)项目提供的灵感和思路

![简单支付](https://images.gitee.com/uploads/images/2021/0726/160007_380bb4a4_524686.png "简单支付.png")

### 组合支付演示
> 可以自由组合钱包余额、现金、支付宝或微信支付进行支付
> 
> [组合支付演示地址](http://web.cloud.bootx.cn/#/demo)
> 
> 演示页面接口对接中，先放上原型

![原型](https://images.gitee.com/uploads/images/2021/0723/174435_c28be310_524686.png "原型图.png")

## 🥞 系统架构

> 技术选型

- 编程语言：Java8+、Groovy、JavaScript
- 核心框架：Spring Boot、Spring Cloud、Spring Cloud Alibaba
- 持久层框架：MyBatis Plus
- 消息中间件：RabbitMQ
- 日志管理：Logstash-logback、Filebeat、ElasticSearch、Kibana
- 分布式中间件：Nacos、Sentinel、Sleuth、Zipkin、Stata

> 核心依赖

| 依赖                       | 版本     | 描述                          |
| -------------------------- | -------- | ----------------------------- |
| Spring Boot                | 2.5.4    |                               |
| Spring Cloud               | 2020.0.3 |                               |
| Spring Cloud Alibaba       | 2021.1   |                               |
| Mybatis Plus               | 3.4.3   | 持久层框架                 |
| Nacos                      | 2.0.2    | 替换为Nacos2.X                |
| Seata                      | 1.4.1    | 分布式事务                    |
| kryo                       | 0.41     | 序列化框架，seata在使用       |
| transmittable-thread-local | 2.12.1   |                               |

> 项目结构

```lua
bootx-cloud
├── bootx-common-core -- 系统公共模块
├── bootx-common-starters -- 通用功能组件模块
     └──  common-starter-auth  -- 认证功能模块
├── bootx-commons  -- 公共功能组件模块
     ├── common-auth -- 认证工具类模块
     ├── common-cache -- 缓存模块
     ├── common-exception-handler -- web异常处理模块
     ├── common-feign -- feign扩展模块
     ├── common-header-holder -- 请求头数据获取模块
     ├── common-idempotency -- 幂等工具模块
     ├── common-jackson -- jackson默认配置模块
     ├── common-jpa -- jpa功能扩展模块
     ├── common-lock -- 分布式锁工具    
     ├── common-log -- 日志扩展模块
     ├── common-mq -- 消息队列模块
     ├── common-mybatis-plus -- mybatis-plus功能扩展模块
     ├── common-redis-client -- redis操作功能模块
     ├── common-seata -- 分布式事务扩展模块
     ├── common-sequence -- 发号器模块
     ├── common-snowflake -- 雪花ID模块
     ├── common-spring -- spring扩展模块
     └── common-xxljob -- 定时任务模块
├── bootx-gateway -- Spring Cloud Gateway网关(9000)
├── service-parent -- 
     ├── bootx-parent-api -- 服务API模块公共依赖
     ├── bootx-parent-impl -- 服务实现模块公共依赖
     ├── bootx-parent-one -- 项目自身模块(一方库)版本控制
     └── bootx-parent-tripartite -- 项目第三方依赖(三方库)版本管理
├──bootx-service-base -- 基础服务集合
     ├── service-baseapi -- 基础api服务(9201)
     ├── service-bsp -- 基础服务平台(9001)
     ├── service-iam -- 身份识别与访问管理(9005)
     └── service-notice -- 通知中心(9202)
├──bootx-service-shop
     ├── service-goods -- 商品中心(9501)
     ├── service-payment -- 支付中心(9502)
     ├── service-sales -- 销售中心(9503)
     └── service-order -- 订单中心(9504)
└── service-shop-engine -- 商城引擎(9901)

```

>系统架构图
![系统架构图](https://images.gitee.com/uploads/images/2021/0707/230002_0ab2d9b1_524686.png "系统架构图.png")


## 🍇项目特点

- 统一网关，实现鉴权、限流、黑白名单、访问记录等功能

- 分布式项目，前后端分离架构，方便二次开发

- 适用ELK进行日志管理，方便进行日志追踪

- 提供对常见容器化支持 `Docker`、`docker-compose`、`Kubernetes`支持

- 各子系统之间的调用使用`feign` 和 `MQ` 实现，保证了高可用、消息可达

- 支持多种登录方式，如`账号密码`，`手机验证码登陆`、`第三方登录`等

- 支持`站内信` `邮件` `短信` `钉钉` `微信` 等消息通知类型

- 多渠道支付，已支持`微信` `支付宝` `电子钱包` `现金` 等，下一步支持 `云闪付` `代金券`  等

- 支持组合支付，可以同时使用一种异步支付方式和多种同步支付方式，进行组合支付

- 支持自动与支付网关进行对账，对漏单、错单等异常情况进行预警

- 订单根据支付方式拆分成账单账单，方便统计各渠道收入

- 自定义促销策略、灵活创建各种各样的促销活动和优惠券活动

- 支持`满减` `折扣` `立减` `会员价` `打包价` `首单价`等优惠策略，可以灵活扩展

- 根据传入的订单自动推荐适用的优惠券和活动

- 支持配置不同活动和优惠劵之间的 `叠加` `互斥` 规则

- 完善的库存管理，支持预占库存、库存释放、库存扣减、增加库存、库存超卖、库存预警等操作



##  🥪 关于我们

微信扫码加入交流群，或添加微信号：`xxxx` 邀请进群


钉钉扫码加入钉钉交流群


QQ扫码加入QQ交流群
<p>
<img src="https://images.gitee.com/uploads/images/2021/0706/221552_934eb540_524686.png" width = "330" height = "500"/>
</p>

##  🍷License

Apache License Version 2.0

## 🥂其他

- star趋势 

[![Stargazers over time](https://whnb.wang/stars/bootx/bootx-cloud)](https://whnb.wang)
