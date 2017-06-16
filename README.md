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