package com.kang98.service.serviceproduct.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.kang98.service.serviceproduct")
@PropertySource("classpath:/application.properties")
public class ProductServiceConfig {
}
