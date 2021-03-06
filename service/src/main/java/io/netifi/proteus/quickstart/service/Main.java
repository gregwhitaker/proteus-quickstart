package io.netifi.proteus.quickstart.service;

import io.netifi.proteus.quickstart.service.protobuf.HelloServiceServer;
import io.netifi.sdk.Netifi;

import java.util.UUID;

/**
 * Starts the Proteus Quickstart Server
 */
public class Main {

    public static void main(String... args) throws Exception {
        String serviceName = "helloservice-" + UUID.randomUUID().toString();

        // Build Netifi Connection
        Netifi netifi =
                Netifi.builder()
                        .group("quickstart.services.helloservices")     // Group name of service
                        .destination(serviceName)                       // Name of this service instance
                        .accountId(100)
                        .accessKey(7685465987873703191L)
                        .accessToken("PYYgV9XHSJ/3KqgK5wYjz+73MeA=")
                        .host("localhost")                              // Proteus Router Host
                        .port(8001)                                     // Proteus Router Port
                        .build();

        // Add Service to Respond to Requests
        netifi.addService(new HelloServiceServer(new DefaultHelloService(serviceName)));

        // Connect to Netifi Proteus Platform
        netifi.connect("quickstart.services.helloservices").block();

        // Keep the Service Running
        Thread.currentThread().join();
    }
}
