package com.kang98.service.servicedeliveryman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("com.kang98.data.dataorder.repository")
public class DeliverymanServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeliverymanServiceApplication.class, args);
    }
}