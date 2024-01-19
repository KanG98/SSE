package com.kang98.service.serviceorder.controller;

import com.kang98.service.serviceorder.dto.UpdateOrderStatusRequest;
import com.kang98.service.serviceorder.service.UpdateOrdersService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateOrdersControllerTest {

    @InjectMocks
    private UpdateOrdersController updateOrdersController;

    @Mock
    private UpdateOrdersService updateOrdersService;

    @Test
    public void updateOrder_callMethod_expectedOrderUpdated() {
        UpdateOrderStatusRequest mockRequest = UpdateOrderStatusRequest.builder().orderId("id").orderStatus("status").build();
        when(updateOrdersService.updateOrderStatus(anyString(), anyString())).thenReturn(true);

        assertAll("Update order",
                () -> assertEquals(updateOrdersController.updateOrderStatus(mockRequest).isDbAcknowledgement(), true),
                () -> verify(updateOrdersService, times(1)).updateOrderStatus(anyString(), anyString())
        );
    }
}
