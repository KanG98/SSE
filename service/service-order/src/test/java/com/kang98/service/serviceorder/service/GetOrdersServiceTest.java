package com.kang98.service.serviceorder.service;

import com.kang98.data.dataorder.entity.Order;
import com.kang98.data.dataorder.repository.OrdersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetOrdersServiceTest {

    @Mock
    private OrdersRepository ordersRepository;

    @InjectMocks
    private GetOrdersService getOrdersService;

    @Test
    void getAllProducts_callMethod_expectedReadProductsResponse() {
        Order mockOrder = Order.builder().customerId("id").build();
        when(ordersRepository.findAll()).thenReturn(Arrays.asList(mockOrder));
        assertAll("Get all orders",
                () -> assertEquals(getOrdersService.getAllOrders(), Arrays.asList(mockOrder)),
                () -> verify(ordersRepository, times(1)).findAll()
        );
    }

}
