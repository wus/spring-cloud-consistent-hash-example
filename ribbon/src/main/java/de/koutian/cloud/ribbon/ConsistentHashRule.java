package de.koutian.cloud.ribbon;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

public class ConsistentHashRule extends AbstractLoadBalancerRule {
	
    private static Logger log = LoggerFactory.getLogger(ConsistentHashRule.class);

    @Inject
    KeyHolder keyHolder;
    
    ConsistentHash cHash = new ConsistentHash();

    public Server choose(ILoadBalancer lb, Object key) {
    	final String hashKey = keyHolder.getKey();
        if (lb == null) {
            log.warn("no load balancer");
            return null;
        }
        Server server = null;
        int count = 0;
        
        while (server == null && count++ < 10) {
            List<Server> reachableServers = lb.getReachableServers();
            cHash.update(reachableServers);
            if ((reachableServers.size() == 0)) {
                log.warn("No up servers available from load balancer: " + lb);
                return null;
            }
            server = cHash.get(Long.valueOf(hashKey));

            if (server == null) {
                /* Transient. */
                Thread.yield();
                continue;
            }

            if (server.isAlive() && (server.isReadyToServe())) {
                return (server);
            }

            // Next.
            server = null;
        }

        if (count >= 10) {
            log.warn("No available alive servers after 10 tries from load balancer: "
                    + lb);
        }
        return server;
    }

	@Override
	public Server choose(Object key) {
		// TODO Auto-generated method stub
		return choose(getLoadBalancer(), key);
	}

	@Override
	public void initWithNiwsConfig(IClientConfig clientConfig) {
	}

}
