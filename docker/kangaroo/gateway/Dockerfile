# 基础镜像
FROM  openjdk:11-jre
# author
MAINTAINER kangaroo

ENV TZ 'Asia/Shanghai'
#ENV LANG C.UTF-8
#ENV LC_ALL en_US.UTF-8

# 挂载目录
VOLUME /home/kangaroo
# 创建目录
RUN mkdir -p /home/kangaroo
# 指定路径
WORKDIR /home/kangaroo
# 复制jar文件到路径
COPY ./jar/kangaroo-gateway.jar /home/kangaroo/kangaroo-gateway.jar
# 启动网关服务
ENTRYPOINT ["java","-jar","kangaroo-gateway.jar"]