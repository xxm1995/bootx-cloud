FROM registry.cn-beijing.aliyuncs.com/xxm1995/java8

MAINTAINER xxm1995@outlook.com

ENV TZ=Asia/Shanghai
ENV JAVA_OPTS="-Xms128m -Xmx512m -XX:+UseG1GC -Djava.security.egd=file:/dev/./urandom"

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

WORKDIR /

EXPOSE 9601 29601

ADD service-shop-engine-impl/target/service-shop-engine-impl.jar service-shop-engine-impl.jar

CMD java $JAVA_OPTS -jar service-shop-engine-impl.jar