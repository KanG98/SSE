package com.kang98.service.servicedeliveryman.controller;

import com.kang98.data.datadeliveryman.entity.Deliveryman;
import com.kang98.service.servicedeliveryman.service.GetDeliverymanService;
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
public class GetDeliverymanControllerTest {
    @Mock
    private GetDeliverymanService getDeliverymanService;

    @InjectMocks
    private GetDeliverymenController getDeliverymenController;

    @Test
    void getAllDeliverymen_callMethod_expectedDeliverymenList() {
        Deliveryman mockDeliveryman = Deliveryman.builder().id("id").build();
        when(getDeliverymanService.getAllDeliverymen()).thenReturn(Arrays.asList(mockDeliveryman));

        assertAll("Get deliverymen list",
                () -> assertEquals(getDeliverymanService.getAllDeliverymen(), Arrays.asList(mockDeliveryman)),
                () -> verify(getDeliverymanService, times(1)).getAllDeliverymen()
        );
    }

    @Test
    void getDeliverymanByPhoneNumber_callMethod_expectedDeliverymenList() {
        Deliveryman mockDeliveryman = Deliveryman.builder().phoneNumber("123").build();
        when(getDeliverymanService.getAllDeliverymanByPhoneNumber(anyString())).thenReturn(Arrays.asList(mockDeliveryman));

        assertAll("Get deliveryman by phone number",
                () -> assertEquals(getDeliverymanService.getAllDeliverymanByPhoneNumber("123"), Arrays.asList(mockDeliveryman)),
                () -> verify(getDeliverymanService, times(1)).getAllDeliverymanByPhoneNumber(anyString())
        );
    }
}
