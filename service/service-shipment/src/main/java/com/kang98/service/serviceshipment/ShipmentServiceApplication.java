package com.kang98.service.serviceshipment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("com.kang98.data.datashipment.repository")
@EnableJpaRepositories("com.kang98.data.dataauth.repository")
public class ShipmentServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShipmentServiceApplication.class, args);
    }
}
