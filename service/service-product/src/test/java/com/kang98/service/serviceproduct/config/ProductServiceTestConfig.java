package com.kang98.service.serviceproduct.config;

import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Import(ProductServiceConfig.class)
@PropertySource("classpath:/application.properties")
@ComponentScan("com.kang98.data.dataauth")
@AutoConfigureDataMongo
public class ProductServiceTestConfig {
    public static final String TEST_GET_PRODUCTS_RESPONSE = "/get-products-service-response.json";

    public static final String TEST_GET_PRODUCTS_REQUEST = "/get-products-service-request.json";

    public static final String TEST_GET_ALL_PRODUCTS_RESPONSE = "/get-all-products-service-response.json";
}
