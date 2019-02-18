package com.example;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.grizzly.threadpool.ThreadPoolConfig;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.model.Resource;
import org.glassfish.jersey.server.model.ResourceModelIssue;

import java.io.IOException;
import java.net.URI;
import java.util.LinkedList;
import java.util.stream.IntStream;

/**
 * Main class.
 *
 */
public class Main {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8080/myapp/";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in com.example package
        final ResourceConfig rc = new ResourceConfig().packages("com.example").register(MyFeature.class);

        // Create 400 fake resources to illustrate the performance impact
        IntStream.range(1, 400).forEachOrdered(n -> {
            Resource.Builder resourceBuilder = Resource.builder(MyResource.class).path("resource" + String.valueOf(n));
            rc.registerResources(resourceBuilder.build());
        });


        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();

        System.out.println(String.format("Jersey app started at "
                + "%sresource1\nHit enter to stop it...", BASE_URI));
        System.in.read();
        server.stop();
    }
}

