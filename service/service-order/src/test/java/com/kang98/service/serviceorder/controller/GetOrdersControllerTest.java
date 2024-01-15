package com.kang98.service.serviceorder.controller;

import com.kang98.data.dataorder.entity.Order;
import com.kang98.data.dataorder.entity.OrderStatusDetail;
import com.kang98.service.serviceorder.dto.GetOrdersStatusesRequest;
import com.kang98.service.serviceorder.dto.GetOrdersStatusesResponse;
import com.kang98.service.serviceorder.service.GetOrdersService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetOrdersControllerTest {

    @Mock
    private GetOrdersService getOrdersService;

    @InjectMocks
    private GetOrdersController getOrdersController;

    @Test
    void getAllOrders_givenValidRequest_expectedOrders() throws IOException {
        List<Order> mockOrders = Arrays.asList(Order.builder().customerId("not in db").build());
        when(getOrdersService.getAllOrders()).thenReturn(mockOrders);
        List<Order> actualOrders = getOrdersController.getAllOrders().getOrders();
        Order actual = actualOrders.get(0);

        assertAll("Get all orders",
                () -> assertTrue(actualOrders.size() == 1),
                () -> assertTrue(actual.getCustomerId().equals("not in db")),
                () -> verify(getOrdersService, times(1)).getAllOrders()
        );
    }

    @Test
    void getOrderStatusDetailsById_givenValidRequest_expectedSuccess () {
        OrderStatusDetail mockOrderStatusDetail = OrderStatusDetail.builder().orderId("notInDB").orderStatus("ok").build();
        when(getOrdersService.getOrdersStatusDetailsByOrderId(any(String.class))).thenReturn(mockOrderStatusDetail);
        GetOrdersStatusesResponse actual = getOrdersController.getOrderStatusesByOrderId(GetOrdersStatusesRequest.builder().orderId("notInDB").build());

        assertAll("Get order statuses by order id",
                () -> assertTrue(actual.getOrderId().equals("notInDB")),
                () -> assertTrue(actual.getOrderStatus().equals("ok")),
                () -> verify(getOrdersService, times(1)).getOrdersStatusDetailsByOrderId(any(String.class))
        );
    }

}
