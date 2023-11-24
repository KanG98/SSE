package com.kang98.service.serviceorder.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kang98.data.dataorder.entity.Order;
import com.kang98.data.dataproduct.entity.Product;
import com.kang98.service.serviceorder.dto.CreateOrdersRequest;
import com.kang98.service.serviceorder.service.CreateOrdersService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import static com.kang98.service.serviceorder.config.OrderServiceTestConfig.TEST_CREATE_ORDER_REQUEST;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateOrdersControllerTest {

    @Mock
    private CreateOrdersService createOrdersService;

    @InjectMocks
    private CreateOrdersController createOrdersController;

    @Test
    void createOrder_givenValidRequest_expectedCreateOrder() throws IOException {
        InputStream requestJson = this.getClass().getResourceAsStream(TEST_CREATE_ORDER_REQUEST);
        var createOrdersRequest = new ObjectMapper().readValue(requestJson, CreateOrdersRequest.class);

        when(createOrdersService.createOrder(any(Order.class))).thenReturn(Order.builder().build());

        assertAll("Create order success",
                () -> createOrdersController.createOrder(createOrdersRequest),
                () -> verify(createOrdersService, times(1)).createOrder(any(Order.class))
        );
    }
}
