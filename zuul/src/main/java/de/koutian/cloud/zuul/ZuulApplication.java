package de.koutian.cloud.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
public class ZuulApplication {

    public static void main(final String[] args) {
        /*
         * run application
         */
        SpringApplication.run(ZuulApplication.class, args);
    }

}
