package com.bank_account.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableJpaRepositories
public class BankApplication {
    public static void main(String[] args) {
        SpringApplication.run(BankApplication.class,args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
      return new RestTemplate();
    }
}