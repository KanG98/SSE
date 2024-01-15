package com.kang98.service.serviceorder.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kang98.data.dataorder.entity.Order;
import com.kang98.data.dataorder.entity.OrderStatus;
import com.kang98.service.serviceorder.dto.CreateOrdersRequest;
import com.kang98.service.serviceorder.dto.GetOrdersResponse;
import com.kang98.service.serviceorder.dto.GetOrdersStatusesRequest;
import com.kang98.service.serviceorder.dto.GetOrdersStatusesResponse;
import com.kang98.service.serviceorder.service.CreateOrdersService;
import com.kang98.service.serviceorder.service.GetOrdersService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.kang98.service.serviceorder.config.OrderServiceTestConfig.TEST_CREATE_ORDER_REQUEST;
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
    void getOrderStatusesById_givenValidRequest_expectedSuccess () {
        OrderStatus mockOrderStatus = OrderStatus.builder().orderId("notInDB").orderStatus("ok").build();
        when(getOrdersService.getOrdersStatusesByOrderId(any(String.class))).thenReturn(mockOrderStatus);
        GetOrdersStatusesResponse actual = getOrdersController.getOrderStatusesByOrderId(GetOrdersStatusesRequest.builder().orderId("notInDB").build());

        assertAll("Get order statuses by order id",
                () -> assertTrue(actual.getOrderId().equals("notInDB")),
                () -> assertTrue(actual.getOrderStatus().equals("ok")),
                () -> verify(getOrdersService, times(1)).getOrdersStatusesByOrderId(any(String.class))
        );
    }

}
