package com.kang98.service.serviceorder.service;

import com.kang98.data.dataorder.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageListener {

    Logger log = LoggerFactory.getLogger(KafkaMessageListener.class);

    @KafkaListener(topics = "sse-create-orders-service", groupId = "sse-create-orders-service")
    public void consumeEvents(Order order) {
        log.info("consumer consume the events {} ", order.toString());
    }

//    @KafkaListener(topics = "sse-create-orders-service", groupId = "sse-create-orders-service")
//    public void consumeEvents2(Order order) {
//        log.info("consumer2 consume the events {} ", order.toString());
//    }
//
//    @KafkaListener(topics = "sse-create-orders-service", groupId = "sse-create-orders-service")
//    public void consumeEvents3(Order order) {
//        log.info("consumer3 consume the events {} ", order.toString());
//    }
}