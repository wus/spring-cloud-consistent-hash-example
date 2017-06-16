package de.koutian.cloud.ribbon;

import javax.inject.Inject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@SpringBootApplication
@RibbonClient(name = "helloworld-service", configuration = HelloWorldServiceConfiguration.class)
public class RibbonApplication {

    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Inject
    RestTemplate restTemplate;

    @Inject
    KeyHolder keyHolder;

    @Retryable
    @RequestMapping("/hi")
    public String hi(@RequestParam(value = "cardNumber", defaultValue = "1111111111") String cardNumber) {
    	keyHolder.setKey(cardNumber);
        String sayHello = this.restTemplate.getForObject("http://helloworld-service/helloworld-service/rest/sayhello?cardNumber=" + cardNumber, String.class);
        keyHolder.removeKey();
        return String.format("%s, %s!", sayHello, cardNumber);
    }

    public static void main(final String[] args) {
        /*
         * run application
         */
        SpringApplication.run(RibbonApplication.class, args);
    }

}
