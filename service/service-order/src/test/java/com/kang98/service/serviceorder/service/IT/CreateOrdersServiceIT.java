package com.kang98.service.serviceorder.service.IT;

import com.kang98.data.dataorder.entity.Order;
import com.kang98.data.dataorder.repository.OrdersRepository;
import com.kang98.service.serviceorder.service.CreateOrdersService;
import com.kang98.service.serviceorder.service.GetOrdersService;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootTest
public class CreateOrdersServiceIT {
    @Autowired
    private CreateOrdersService createOrdersService;

    @Autowired
    private GetOrdersService getOrdersService;

    @Autowired
    private OrdersRepository ordersRepository;
    private static Date date = new Date();

    @BeforeAll
    static void init() {
        date.setTime(System.currentTimeMillis());
    }

    @Test
    void createOrder_callMethod_expectedOrderCreated() {

        Order mockOrder = Order.builder().scheduledDelivery(date).build();
        Order createdOrder = createOrdersService.createOrder(mockOrder);


        Stream<Order> orders = getOrdersService.getAllOrders().stream();
        assert orders.anyMatch(order -> order.getScheduledDelivery().equals(date));

        ordersRepository.delete(createdOrder);
    }
}
