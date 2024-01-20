package com.kang98.service.serviceorder.service.kafkaconsumers;

import com.kang98.data.dataorder.entity.Order;
import com.kang98.data.dataorder.repository.OrdersRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RepositoryConsumer {

    private OrdersRepository ordersRepository;

    @Autowired
    RepositoryConsumer(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @KafkaListener(topics = "sse-create-orders-service", groupId = "save-order")
    public void consumeEvents(Order order) {
        log.info("Saving order {} ", order.toString());
        ordersRepository.save(order);
        log.info("Saved order {} ", order.toString());
    }
}
