FROM java:8-jdk
RUN mkdir /app
WORKDIR /app
COPY eureka-server.jar /app
ENTRYPOINT ["java", "-jar", "/app/eureka-server.jar", "--spring.profiles.active=docker"]
EXPOSE 8761