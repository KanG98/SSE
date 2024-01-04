package com.kang98.service.servicedeliveryman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("com.kang98.data.datadeliveryman.repository")
@EnableJpaRepositories("com.kang98.data.dataauth.repository")
public class DeliverymanServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeliverymanServiceApplication.class, args);
    }
}