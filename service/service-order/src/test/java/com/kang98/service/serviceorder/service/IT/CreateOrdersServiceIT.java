package com.kang98.service.serviceorder.service.IT;

import com.kang98.data.dataorder.entity.Order;
import com.kang98.data.dataorder.repository.OrdersRepository;
import com.kang98.service.serviceorder.service.CreateOrdersService;
import com.kang98.service.serviceorder.service.GetOrdersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CreateOrdersServiceIT {
    @Autowired
    private CreateOrdersService createOrdersService;

    @Autowired
    private GetOrdersService getOrdersService;

    @Autowired
    private OrdersRepository ordersRepository;

    @Test
    void createOrder_callMethod_expectedOrderCreated() {

        Order mockOrder = Order.builder().customerId("IT").build();
        Order createdOrder = createOrdersService.createOrder(mockOrder);

        assertEquals(createdOrder.getCustomerId(), mockOrder.getCustomerId());
        Stream<Order> orders = getOrdersService.getAllOrders().stream();
        assert orders.anyMatch(order -> order.getCustomerId().equals("IT"));

        ordersRepository.deleteByCustomerId(createdOrder.getCustomerId());
    }
}
