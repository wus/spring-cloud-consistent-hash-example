# spring-cloud-consistent-hash-example

spring-cloud-consistent-hash-example is an example for client load balancer(ribbon) with custom consistent hash algorithm.

## Getting Started
gradle buildDocker
docker-compose up -d

upscaling downscaling helloworld-service, use:
docker-compose scale helloworld-service=n

## Technology List
* Spring Cloud Netflix (Eureka, Ribbon)
* Spring Boot
* Docker

## Urls
* Eureka http://localhost:8761/
* Ribbon Load Balancer http://localhost:8888/hi?cardNumber=130
* Zuul Load Balancer http://localhost:8060/helloworld-service/helloworld-service/rest/sayhello?cardNumber=123456789
