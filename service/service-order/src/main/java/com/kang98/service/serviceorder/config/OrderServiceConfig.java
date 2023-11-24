package com.kang98.service.serviceorder.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.kang98.service.serviceorder")
@PropertySource("classpath:/application.properties")
public class OrderServiceConfig {
}
