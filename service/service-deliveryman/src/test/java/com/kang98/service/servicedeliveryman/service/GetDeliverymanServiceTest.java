package com.kang98.service.servicedeliveryman.service;

import com.kang98.data.datadeliveryman.entity.Deliveryman;
import com.kang98.data.datadeliveryman.repository.DeliverymenRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetDeliverymanServiceTest {

    @Mock
    private DeliverymenRepository deliverymenRepository;

    @InjectMocks
    private GetDeliverymanService getDeliverymanService;

    @Test
    void getAllProducts_callMethod_expectedDeliverymenList() {
        Deliveryman mockDeliveryman = Deliveryman.builder().id("id").build();
        when(deliverymenRepository.findAll()).thenReturn(Arrays.asList(mockDeliveryman));

        assertAll("Get deliverymen list",
                () -> assertEquals(getDeliverymanService.getAllProducts(), Arrays.asList(mockDeliveryman)),
                () -> verify(deliverymenRepository, times(1)).findAll()
        );
    }
}
