package com.kang98.service.serviceshipment.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.*;

@Configuration
//@PropertySource("classpath:/application.properties")
@EntityScan({"com.kang98.data.dataauth.entity"})
@Import(ShipmentSecurityConfig.class)
public class ShipmentServiceConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


}
