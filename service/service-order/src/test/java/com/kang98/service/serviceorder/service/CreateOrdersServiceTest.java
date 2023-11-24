package com.kang98.service.serviceorder.service;

import com.kang98.data.dataorder.entity.Order;
import com.kang98.data.dataorder.repository.OrdersRepository;
import com.kang98.service.serviceorder.service.CreateOrdersService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateOrdersServiceTest {
    @Mock
    private OrdersRepository ordersRepository;

    @InjectMocks
    private CreateOrdersService createOrdersService;

    @Test
    void getAllProducts_callMethod_expectedReadProductsResponse() {
        Order mockOrder = Order.builder().customerId("id").build();
        when(ordersRepository.save(any(Order.class))).thenReturn(mockOrder);

        assertAll("Create order",
                () -> assertEquals(createOrdersService.createOrder(mockOrder), mockOrder),
                () -> verify(ordersRepository, times(1)).save(any(Order.class))
        );
    }
}
