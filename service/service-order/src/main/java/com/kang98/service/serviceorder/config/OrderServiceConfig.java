package com.kang98.service.serviceorder.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/application.properties")
@ComponentScan({"com.kang98.data.dataauth.repository",
        "com.kang98.service.serviceauth.service.helpers",
        "com.kang98.service.serviceorder"})
@EntityScan({"com.kang98.data.dataauth.entity"})
public class OrderServiceConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
