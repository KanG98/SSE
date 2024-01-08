package com.kang98.service.serviceorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories({"com.kang98.data.dataorder.repository",
        "com.kang98.data.datadeliveryman.repository",
        "com.kang98.data.datashipment.repository"})
@EnableJpaRepositories("com.kang98.data.dataauth.repository")
public class OrderServiceApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}