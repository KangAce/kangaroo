# 基础镜像
FROM  openjdk:11-jre
# author
MAINTAINER kangaroo

ENV TZ 'Asia/Shanghai'
ENV LANG en_US.UTF-8
ENV LANGUAGE en_US:en
ENV LC_ALL en_US.UTF-8

# 挂载目录
VOLUME /home/kangaroo
# 创建目录
RUN mkdir -p /home/kangaroo
# 指定路径
WORKDIR /home/kangaroo
# 复制jar文件到路径
COPY ./jar/kangaroo-modules-job.jar /home/kangaroo/kangaroo-modules-job.jar
# 启动定时任务服务
ENTRYPOINT ["java","-jar","kangaroo-modules-job.jar"]