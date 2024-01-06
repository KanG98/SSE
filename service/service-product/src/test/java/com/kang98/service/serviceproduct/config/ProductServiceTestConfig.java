package com.kang98.service.serviceproduct.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.*;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;

@Configuration
@Import(ProductServiceConfig.class)
@PropertySource("classpath:/application.properties")
//@ComponentScan({"com.kang98.data.dataauth", "com.kang98.service.serviceproduct"})
@ComponentScan({"com.kang98.service.serviceproduct"})
//@EntityScan("com.kang98.data.dataauth.entity")
@AutoConfigureDataMongo
public class ProductServiceTestConfig {
    public static final String TEST_GET_PRODUCTS_RESPONSE = "/get-products-service-response.json";

    public static final String TEST_GET_PRODUCTS_REQUEST = "/get-products-service-request.json";

    public static final String TEST_GET_ALL_PRODUCTS_RESPONSE = "/get-all-products-service-response.json";

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
        sessionFactory.setPackagesToScan("com.kang98.data.dataauth.entity");

        return sessionFactory;
    }

}
