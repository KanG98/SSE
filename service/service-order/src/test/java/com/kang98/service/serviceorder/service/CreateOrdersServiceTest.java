package com.kang98.service.serviceorder.service;

import com.kang98.data.dataorder.entity.Order;
import com.kang98.data.dataorder.repository.OrdersRepository;
import com.kang98.foundation.kafka.Producer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CreateOrdersServiceTest {
    @Mock
    private OrdersRepository ordersRepository;

    @Mock
    private Producer producer;

    @InjectMocks
    private CreateOrdersService createOrdersService;

    @Test
    void getAllProducts_callMethod_expectedReadProductsResponse() {
        Order mockOrder = Order.builder().customerId("id").build();

        doNothing().when(producer).sendEventsToTopic(any(), anyString(), any());

        assertAll("Create order",
                () -> createOrdersService.createOrder(mockOrder),
                () -> verify(producer, times(1)).sendEventsToTopic(any(), anyString(), any())
        );
    }
}
