package com.kang98.service.serviceorder.service;

import com.kang98.data.dataorder.entity.Order;
import com.kang98.data.dataorder.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateOrdersService {

    private final OrderRepository orderRepository;

    public void createOrder(Order order) {
        orderRepository.save(order);
    }
}
