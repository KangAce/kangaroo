# 基础镜像
FROM redis
# author
MAINTAINER kangaroo

# 挂载目录
VOLUME /home/kangaroo/redis
# 创建目录
RUN mkdir -p /home/kangaroo/redis
# 指定路径
WORKDIR /home/kangaroo/redis
# 复制conf文件到路径
COPY ./conf/redis.conf /home/kangaroo/redis/redis.conf
