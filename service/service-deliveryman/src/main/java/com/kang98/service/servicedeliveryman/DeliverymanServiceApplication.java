package com.kang98.service.servicedeliveryman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("com.kang98.data.dataorder.repository")
@ComponentScan({"com.kang98.data.dataauth.repository",
        "com.kang98.service.serviceauth.service.helpers",
        "com.kang98.service.serviceorder"})
@EntityScan({"com.kang98.data.dataauth.entity"})
public class DeliverymanServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeliverymanServiceApplication.class, args);
    }
}