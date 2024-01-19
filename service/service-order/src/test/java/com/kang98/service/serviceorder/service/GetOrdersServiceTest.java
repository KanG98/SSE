package com.kang98.service.serviceorder.service;

import com.kang98.data.datadeliveryman.entity.Deliveryman;
import com.kang98.data.datadeliveryman.repository.DeliverymenRepository;
import com.kang98.data.dataorder.entity.Order;
import com.kang98.data.dataorder.repository.OrdersRepository;
import com.kang98.data.datashipment.entity.Shipment;
import com.kang98.data.datashipment.repository.ShipmentsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetOrdersServiceTest {

    @Mock
    private OrdersRepository ordersRepository;

    @Mock
    private ShipmentsRepository shipmentsRepository;

    @Mock
    private DeliverymenRepository deliverymenRepository;

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

    @Test
    // need IT for this
    void getOrderStatusDetailById_givenValidRequest_expectedSuccess () {
        lenient().doReturn(Optional.of(Shipment.builder().build())).when(shipmentsRepository).findByOrderId(anyString());
        lenient().doReturn(Optional.of(Deliveryman.builder().id("0").build())).when(deliverymenRepository).findById(anyString());
        lenient().doReturn(Optional.of(Order.builder().orderStatus("on-the-way").build())).when(ordersRepository).findById(anyString());
        var actual = getOrdersService.getOrdersStatusDetailsByOrderId("notInDB");

        assertAll("Get order statuses by order id",
                () -> assertTrue(actual.getOrderId().equals("notInDB")),
                () -> assertTrue(actual.getDeliverymanId().equals("no-deliveryman-assigned")),
                () -> assertTrue(actual.getOrderStatus().equals("on-the-way")),
                () -> verify(shipmentsRepository, times(1)).findByOrderId(any(String.class)),
                () -> verify(deliverymenRepository, times(1)).findById(any(String.class)),
                () -> verify(ordersRepository, times(1)).findById(any(String.class))
        );
    }

}
