# 🍳FAQ
1. 使用maven可以正常构建，但是IDEA出现`程序包cn.bootx.*`不存在，无法进行启动

    - 首先判断pom文件是否已经添加依赖，可以执行`mvn -clear compile`进行测试，如果执行正常说明maven配置没问题
    - 如maven配置无问题，可能是IDEA的问题，可以试试以下的方法
    - 进行移包重构导致的类不识别，可以删除这个类，然后撤销
    - 如果是自动生成的类不识别，查看下`target\generated-sources`文件夹下的目录是否未被标记为代码目录
    - 删除.idea文件夹重新打开项目、清除IDEA缓存，

2. 出现QEntity对象(如QOrder)找不到

   执行命令mvn compile`就可以通过APT生成对应的QEntity对象

3. 启动时Nacos Client报`failed to req API:/nacos/v1/ns/service/list after all servers...`  错误

   替换Spring Cloud Alibaba 默认的 Nacos Client 为2.x版本

4.


