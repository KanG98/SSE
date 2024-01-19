package com.kang98.service.serviceorder.service;

import com.kang98.data.dataorder.entity.Order;
import com.kang98.data.dataorder.repository.OrdersRepository;
import com.kang98.foundation.kafka.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class CreateOrdersService {

    @Autowired
    private KafkaTemplate<String,Object> template;

    @Autowired
    private Producer producer;

    private final OrdersRepository ordersRepository;
    @Autowired
    CreateOrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public Order createOrder(Order order) {
        producer.sendEventsToTopic(template, "sse-create-orders-service", order);
        return ordersRepository.save(order);
    }
}
