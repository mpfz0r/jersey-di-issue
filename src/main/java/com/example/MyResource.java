package com.example;


import org.apache.shiro.authz.annotation.RequiresAuthentication;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
@RequiresAuthentication
public class MyResource {

    @Inject
    private MyService myService;
    @Inject
    private MyService2 myService2;
    @Inject
    private MyService3 myService3;

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got request. myService " + myService.toString() + " myservice2 " + myService2.toString();
    }
}
