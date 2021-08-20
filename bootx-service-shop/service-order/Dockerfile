FROM registry.cn-beijing.aliyuncs.com/xxm1995/java8

MAINTAINER xxm1995@outlook.com

ENV TZ=Asia/Shanghai
ENV JAVA_OPTS="-Xms128m -Xmx512m -XX:+UseG1GC -Djava.security.egd=file:/dev/./urandom"

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

WORKDIR /

EXPOSE 9504 29504

ADD service-order-impl/target/service-order-center-impl.jar service-order-center-impl.jar

CMD java $JAVA_OPTS -jar service-order-center-impl.jar