package com.kang98.service.serviceauth.config;

import com.kang98.service.serviceauth.service.helpers.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import javax.sql.DataSource;
import java.util.Properties;

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

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("SPRING_DATASOURCE_DRIVER_CLASS_NAME"));
        dataSource.setUrl(env.getProperty("SPRING_DATASOURCE_URL"));
        dataSource.setUsername(env.getProperty("SPRING_DATASOURCE_USERNAME"));
        dataSource.setPassword(env.getProperty("SPRING_DATASOURCE_PASSWORD"));
        System.out.println(dataSource.getUrl());
        System.out.println(dataSource.getConnectionProperties());
        System.out.println(dataSource.getUsername());
        System.out.println(dataSource.getPassword());
        return dataSource;
    }
}
