package com.bank_account.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceRegistery {
    public static void main(String[] args) {
        SpringApplication.run(ServiceRegistery.class,args);
    }
}