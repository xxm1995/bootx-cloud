## Jpa 注解
`@EnableJpaRepositories`  如果Jpa相关的代码不在启动类包的路径下, 需要显式声明

`@EnableJpaAuditing`开启审计功能

`@EntityScan`如果Jpa相关的代码不在启动类包的路径下, 需要显式声明


##QueryDSL
- 第一次打开项目时需要先执行maven compile生成Dsl类(QEntity)
- 更新代码后Dsl类报错,mvn clear 后 compile

