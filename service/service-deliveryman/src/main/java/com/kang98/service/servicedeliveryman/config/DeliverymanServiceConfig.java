package com.kang98.service.servicedeliveryman.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DeliverymanSecurityConfig.class)
public class DeliverymanServiceConfig {
}
