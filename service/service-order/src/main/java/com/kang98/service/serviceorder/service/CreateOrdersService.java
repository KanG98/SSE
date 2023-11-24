package com.kang98.service.serviceorder.service;

import com.kang98.data.dataorder.entity.Order;
import com.kang98.data.dataorder.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateOrdersService {

    private final OrdersRepository ordersRepository;

    public Order createOrder(Order order) {
        return ordersRepository.save(order);
    }
}
