FROM mysql/mysql-server:8.0.29

MAINTAINER lengleng(wangiegie@gmail.com)

ENV TZ=Asia/Shanghai

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

COPY ./cadillac.sql /docker-entrypoint-initdb.d

COPY ./cadillac_codegen.sql /docker-entrypoint-initdb.d

COPY ./cadillac_config.sql /docker-entrypoint-initdb.d

COPY ./cadillac_job.sql /docker-entrypoint-initdb.d
