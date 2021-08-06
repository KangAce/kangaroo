# 基础镜像
FROM nginx
# author
MAINTAINER kangaroo

# 挂载目录
VOLUME /home/kangaroo/projects/kangaroo-ui
# 创建目录
RUN mkdir -p /home/kangaroo/projects/kangaroo-ui
# 指定路径
WORKDIR /home/kangaroo/projects/kangaroo-ui
# 复制conf文件到路径
COPY ./conf/nginx.conf /etc/nginx/nginx.conf
# 复制html文件到路径
COPY ./html/dist /home/kangaroo/projects/kangaroo-ui
