FROM java:8-jdk

RUN mkdir /app
WORKDIR /app
COPY zuul.jar /app
ENTRYPOINT ["java", "-jar", "/app/zuul.jar", "--spring.profiles.active=docker"]
EXPOSE 8060