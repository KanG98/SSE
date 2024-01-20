package com.kang98.service.serviceorder.service;

import com.kang98.data.dataorder.entity.Order;
import com.kang98.data.dataorder.repository.OrdersRepository;
import com.kang98.foundation.kafka.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Service
public class CreateOrdersService {

    private KafkaTemplate<String,Object> template;

    private Producer producer;

    @Autowired
    public CreateOrdersService(Producer producer, KafkaTemplate<String,Object> template) {
        this.producer = producer;
        this.template = template;
    }

    public void createOrder(Order order) {
        producer.sendEventsToTopic(template, "sse-create-orders-service", order);
    }
}
