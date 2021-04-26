package com.example.demogatewayjwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class DemoGatewayJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoGatewayJwtApplication.class, args);
    }

}
