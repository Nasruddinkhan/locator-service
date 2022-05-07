package com.mypractice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class SaibLocatorServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaibLocatorServiceApplication.class, args);
    }


    @Bean
    CommandLineRunner runner(){
        return args -> {

        };
    }
}
