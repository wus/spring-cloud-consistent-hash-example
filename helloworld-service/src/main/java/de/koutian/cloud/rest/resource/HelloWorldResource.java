package de.koutian.cloud.rest.resource;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

@Path("/")
public class HelloWorldResource {

    @Inject
    DiscoveryClient discoveryClient;
    
    @GET
    @Path("/sayhello")
    @Produces(MediaType.APPLICATION_JSON)
    public String sayHello(@QueryParam("cardNumber") final String cardNumber) {
        ServiceInstance serviceInstance = discoveryClient.getLocalServiceInstance();
        return serviceInstance.getServiceId() + " (" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + ") " + "says hello to " + cardNumber;
    }

}
