package com.kang98.service.serviceorder.service;

import com.kang98.data.dataorder.entity.Order;
import com.kang98.data.dataorder.repository.OrdersRepository;
import com.kang98.service.serviceorder.dto.GetOrdersResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetOrdersService {

    private OrdersRepository ordersRepository;

    @Autowired
    GetOrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public List<Order> getAllOrders() {
        return ordersRepository.findAll();
    }

}
