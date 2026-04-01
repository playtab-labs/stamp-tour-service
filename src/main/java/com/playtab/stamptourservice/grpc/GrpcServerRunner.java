package com.playtab.stamptourservice.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GrpcServerRunner {

    private final int grpcPort;
    private final StampTourGrpcService stampTourGrpcService;
    private Server server;

    public GrpcServerRunner(
            @Value("${grpc.server.port:9090}") int grpcPort,
            StampTourGrpcService stampTourGrpcService
    ) {
        this.grpcPort = grpcPort;
        this.stampTourGrpcService = stampTourGrpcService;
    }

    // @jakarta.annotation.PostConstruct
    public void start() throws IOException {
        server = ServerBuilder.forPort(grpcPort)
                .addService(stampTourGrpcService)
                .build()
                .start();

        System.out.println("gRPC server started on port " + grpcPort);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (server != null) {
                server.shutdown();
            }
        }));
    }

    @PreDestroy
    public void stop() {
        if (server != null) {
            server.shutdown();
            System.out.println("gRPC server stopped");
        }
    }
}