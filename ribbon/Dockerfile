FROM java:8-jdk

RUN mkdir /app
WORKDIR /app
COPY ribbon.jar /app
ENTRYPOINT ["java", "-jar", "/app/ribbon.jar", "--spring.profiles.active=docker"]
EXPOSE 8060