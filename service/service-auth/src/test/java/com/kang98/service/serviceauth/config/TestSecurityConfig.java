package com.kang98.service.serviceauth.config;

import com.kang98.service.serviceauth.service.helpers.JwtHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@TestConfiguration
@EnableWebSecurity
@EnableMethodSecurity
@Import(AuthServiceConfig.class)
public class TestSecurityConfig {

    @Value("${SECRET:samplesecret}") String secret;

    @Bean
    public JwtHelper jwtService() {
        return new JwtHelper(secret);
    }
}
