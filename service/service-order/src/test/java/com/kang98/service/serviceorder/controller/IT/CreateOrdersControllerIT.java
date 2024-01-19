package com.kang98.service.serviceorder.controller.IT;

import com.kang98.data.dataorder.entity.Order;
import com.kang98.data.dataorder.repository.OrdersRepository;
import com.kang98.service.serviceorder.controller.CreateOrdersController;
import com.kang98.service.serviceorder.dto.CreateOrdersRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CreateOrdersControllerIT {

    @Autowired
    private CreateOrdersController createOrdersController;

    @Autowired
    private OrdersRepository ordersRepository;

    @Test
    void createOrder_callMethod_expectedOrderCreated() {
        CreateOrdersRequest createOrdersRequest = CreateOrdersRequest.builder().customerId("not in db").build();
        createOrdersController.createOrder(createOrdersRequest);
        Optional<List<Order>> orders = ordersRepository.findByCustomerId(createOrdersRequest.getCustomerId());
        assertTrue(orders.isPresent());
        assertEquals(orders.get().get(0).getCustomerId(), createOrdersRequest.getCustomerId());

        ordersRepository.deleteByCustomerId(createOrdersRequest.getCustomerId());
    }
}
