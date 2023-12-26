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
        dataSource.setDriverClassName(env.getProperty("app_datasource_driverClassName"));
        dataSource.setUrl(env.getProperty("app_datasource_url"));
        dataSource.setUsername(env.getProperty("app_datasource_username"));
        dataSource.setPassword(env.getProperty("app_datasource_password"));
        return dataSource;
    }
}
