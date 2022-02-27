# 基础镜像
FROM  openjdk:11-jre
# author
MAINTAINER kangaroo

# 挂载目录
VOLUME /home/kangaroo
# 创建目录
RUN mkdir -p /home/kangaroo
# 指定路径
WORKDIR /home/kangaroo
# 复制jar文件到路径
COPY ./jar/kangaroo-modules-gen.jar /home/kangaroo/kangaroo-modules-gen.jar
# 启动系统服务
ENTRYPOINT ["java","-jar","kangaroo-modules-gen.jar"]