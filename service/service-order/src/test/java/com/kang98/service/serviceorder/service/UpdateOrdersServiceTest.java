package com.kang98.service.serviceorder.service;

import com.kang98.data.dataorder.repository.OrdersRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateOrdersServiceTest {

    @Mock
    private OrdersRepositoryImpl ordersRepositoryImpl;

    @InjectMocks
    private UpdateOrdersService updateOrdersService;

    @Test
    public void updateOrderStatus_givenOrderIdAndStatus_expectedSuccess() {
        when(ordersRepositoryImpl.updateOrderStatus(anyString(), anyString())).thenReturn(true);
        assertAll("Get all orders",
                () -> assertEquals(updateOrdersService.updateOrderStatus("id", "status"), true),
                () -> verify(ordersRepositoryImpl, times(1)).updateOrderStatus(anyString(), anyString())
        );
    }
}
