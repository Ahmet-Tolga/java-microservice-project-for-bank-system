package com.bank_account.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ApiGateway {
    public static void main(String[] args) {
        SpringApplication.run(ApiGateway.class,args);
    }
}