FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD ./docker/fin-gateway-1.0-SNAPSHOT.jar app.jar
ENV TZ Asia/Shanghai
EXPOSE 7000
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]