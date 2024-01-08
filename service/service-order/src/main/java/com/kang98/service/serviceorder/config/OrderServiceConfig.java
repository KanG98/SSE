package com.kang98.service.serviceorder.config;

import com.kang98.data.dataorder.config.OrderDataConfig;
import com.mongodb.ClientSessionOptions;
import com.mongodb.client.*;
import com.mongodb.connection.ClusterDescription;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

@Configuration
@PropertySource("classpath:/local.properties")
@ComponentScan({"com.kang98.service.serviceauth.service.helpers",
        "com.kang98.service.serviceorder",
        "com.kang98.foundation.security",
        "com.kang98.foundation.handler",
        "com.kang98.data.dataorder.repository"
})
@EntityScan({"com.kang98.data.dataauth.entity"})
public class OrderServiceConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
