package com.kang98.service.serviceshipment.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
//@PropertySource("classpath:/application.properties")
@EntityScan({"com.kang98.data.dataauth.entity"})
@Import(ShipmentSecurityConfig.class)
public class ShipmentServiceConfig {
}
