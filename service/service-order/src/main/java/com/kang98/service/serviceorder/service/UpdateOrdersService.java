package com.kang98.service.serviceorder.service;

import com.kang98.data.dataorder.repository.OrdersRepositoryImpl;
import org.springframework.stereotype.Service;

@Service
public class UpdateOrdersService {
    private final OrdersRepositoryImpl orderRepository;

    public UpdateOrdersService(OrdersRepositoryImpl orderRepository) {
        this.orderRepository = orderRepository;
    }

    public boolean updateOrderStatus(String orderId, String orderStatus) {
        return orderRepository.updateOrderStatus(orderId, orderStatus);
    }
}