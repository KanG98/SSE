package com.kang98.service.servicedeliveryman.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({"com.kang98.foundation.handler"})
@Import(DeliverymanSecurityConfig.class)
public class DeliverymanServiceConfig {
}
