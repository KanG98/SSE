package com.kang98.service.servicedeliveryman.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({"com.kang98.foundation.handler", "com.kang98.service.servicedeliveryman"})
@Import(DeliverymanSecurityConfig.class)
public class DeliverymanServiceConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
