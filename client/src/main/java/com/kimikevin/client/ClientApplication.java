package com.kimikevin.client;

import com.kimikevin.client.proto.HelloRequest;
import com.kimikevin.client.proto.SimpleGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    @Bean
    ApplicationRunner clientRunner(@GrpcClient("greetingsService") SimpleGrpc.SimpleBlockingStub simpleBlockingStub) {
        return args -> {
            HelloRequest springFans = HelloRequest.newBuilder().setName("Spring Fans").build();
            System.out.println(simpleBlockingStub.sayHello(springFans));
        };
    }
}