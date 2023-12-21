package com.kang98.service.serviceorder.config;

import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Import(OrderServiceConfig.class)
@PropertySource("classpath:/application.properties")
@AutoConfigureDataMongo
public class OrderServiceTestConfig {
    public static final String TEST_CREATE_ORDER_REQUEST = "/create-order-request.json";

    public static final String TEST_CREATE_ORDER_INVALID_REQUEST = "/create-order-invalid-request.json";
}
