package com.kang98.service.serviceorder.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;

@Configuration
@Import(OrderServiceConfig.class)
@AutoConfigureDataMongo
public class OrderServiceTestConfig {
    public static final String TEST_CREATE_ORDER_REQUEST = "/create-order-request.json";

    public static final String TEST_CREATE_ORDER_INVALID_REQUEST = "/create-order-invalid-request.json";

    @Bean
    public DataSource dataSource() {

        return DataSourceBuilder
                .create()
                .url("jdbc:mysql://localhost:3306/sse_users")
                .username("root")
                .password(null)
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }
    @Bean(name="entityManagerFactory")
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("com.kang98.foundation.security.entity", "com.kang98.data.dataauth.entity");

        return sessionFactory;
    }
}
