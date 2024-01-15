package com.kang98.service.serviceorder.service;

import com.kang98.data.dataorder.entity.Order;
import com.kang98.data.dataorder.entity.OrderStatus;
import com.kang98.data.dataorder.repository.OrdersRepository;
import com.kang98.service.serviceorder.dto.GetOrdersStatusesRequest;
import com.kang98.service.serviceorder.dto.GetOrdersStatusesResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

//    @Test
//    // need IT for this
//    void getOrderStatusesById_givenValidRequest_expectedSuccess () {
//        OrderStatus mockOrderStatus = OrderStatus.builder().orderId("notInDB").orderStatus("ok").build();
//        when(getOrdersService.getOrdersStatusesByOrderId(any(String.class))).thenReturn(mockOrderStatus);
//        GetOrdersStatusesResponse actual = getOrdersController.getOrderStatusesByOrderId(GetOrdersStatusesRequest.builder().orderId("notInDB").build());
//
//        assertAll("Get order statuses by order id",
//                () -> assertTrue(actual.getOrderId().equals("notInDB")),
//                () -> assertTrue(actual.getOrderStatus().equals("ok")),
//                () -> verify(getOrdersService, times(1)).getOrdersStatusesByOrderId(any(String.class))
//        );
//    }

}
