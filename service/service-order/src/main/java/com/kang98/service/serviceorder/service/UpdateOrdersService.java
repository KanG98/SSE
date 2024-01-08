package com.kang98.service.serviceorder.service;

import com.kang98.data.dataorder.repository.OrderRepositoryImpl;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class UpdateOrdersService {
    private final OrderRepositoryImpl orderRepository;
    private final MongoTemplate mongoTemplate;

    public UpdateOrdersService(OrderRepositoryImpl orderRepository, MongoTemplate mongoTemplate) {
        this.orderRepository = orderRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public boolean updateOrderStatus(String orderId, String orderStatus) {
        return orderRepository.updateOrderStatus(mongoTemplate, orderId, orderStatus);
    }
}