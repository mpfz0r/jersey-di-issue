package com.example;


import org.glassfish.grizzly.http.server.Request;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import java.io.IOException;

@Priority(Priorities.AUTHENTICATION)
public class MyFilter implements ContainerRequestFilter {
    public static final String REQUEST_HEADERS = "REQUEST_HEADERS";

    private Provider<Request> grizzlyRequestProvider;


    @Inject
    private MyService myService;

    @Inject
    public MyFilter(Provider<Request> grizzlyRequestProvider) {
       this.grizzlyRequestProvider = grizzlyRequestProvider;
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        final boolean secure = requestContext.getSecurityContext().isSecure();
        System.out.println("FOO " + myService);
    }
}
