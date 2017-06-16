FROM java:8-jdk

RUN mkdir /app
WORKDIR /app
COPY helloworld-service.jar /app
ENTRYPOINT ["java", "-jar", "/app/helloworld-service.jar", "--spring.profiles.active=docker"]
EXPOSE 8080