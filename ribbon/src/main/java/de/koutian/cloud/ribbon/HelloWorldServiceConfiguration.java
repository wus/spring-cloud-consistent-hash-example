package de.koutian.cloud.ribbon;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;

public class HelloWorldServiceConfiguration {
    
    @Inject
    IClientConfig ribbonClientConfig;
 
    @Bean
    public IPing ribbonPing(IClientConfig config) {
        PingUrl pingUrl = new PingUrl();
        pingUrl.setPingAppendString("/health");
		return pingUrl;
    }
 
    @Bean
    public IRule ribbonRule(IClientConfig config) {
        return new ConsistentHashRule();
    }

}
