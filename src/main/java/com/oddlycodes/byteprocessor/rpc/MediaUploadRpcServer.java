package com.oddlycodes.byteprocessor.rpc;

import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MediaUploadRpcServer {

    private final Logger logger = LoggerFactory.getLogger(MediaUploadRpcServer.class);

    @Value("{grpc.server.port}")
    private int port;

    private Server server;

    public void initServer() {

        server = Grpc.newServerBuilderForPort(port, InsecureServerCredentials.create()).build();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                if (server != null) {
                    try {
                        server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
                    } catch (InterruptedException ie) {
                        logger.error(ie.getMessage());
                    }
                }
            }
        });

    }

    public void start() throws IOException {

        if (server != null) {
            server.start();
        }

    }

    public void stop() throws InterruptedException {

        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }

    }

}
