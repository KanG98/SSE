package com.kang98.service.serviceorder.service;

import com.kang98.data.dataorder.repository.OrderRepositoryImpl;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class UpdateOrdersService {
    private final OrderRepositoryImpl orderRepository;

    public UpdateOrdersService(OrderRepositoryImpl orderRepository) {
        this.orderRepository = orderRepository;
    }

    public boolean updateOrderStatus(String orderId, String orderStatus) {
        return orderRepository.updateOrderStatus(orderId, orderStatus);
    }
}