package de.koutian.cloud.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@EnableEurekaClient
@SpringBootApplication
@ComponentScan("de.koutian.cloud")
public class Application {

    public static void main(final String[] args) {
        /*
         * run application
         */
        SpringApplication.run(Application.class, args);
    }

}
