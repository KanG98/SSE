package com.kang98.service.serviceproduct.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/local.properties")
@ComponentScan({"com.kang98.data.dataauth.repository",
        "com.kang98.service.serviceproduct",
        "com.kang98.service.serviceorder"})
@EntityScan({"com.kang98.data.dataauth.entity"})
@Import(ProductSecurityConfig.class)
public class ProductServiceConfig {
}
