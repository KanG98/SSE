package com.kang98.service.serviceshipment.controller;

import com.kang98.data.datashipment.entity.Shipment;
import com.kang98.service.serviceshipment.dto.CreateShipmentRequest;
import com.kang98.service.serviceshipment.service.CreateShipmentService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CreateShipmentControllerTest {

    @InjectMocks
    private CreateShipmentController createShipmentController;

    @Mock
    private CreateShipmentService createShipmentService;

    @Test
    @Disabled
    void createShipment_callMethod_expectedSuccess() {
        Shipment mockShipment = Shipment.builder().orderId("0").deliverymanId(Arrays.asList("1")).build();
        when(createShipmentService.createShipment(any(Shipment.class))).thenReturn(mockShipment);

        CreateShipmentRequest createShipmentRequest = CreateShipmentRequest.builder().orderId("0").deliverymanId("2").build();
        assertAll("create shipment",
                () -> createShipmentController.createShipment(createShipmentRequest),
                () -> verify(createShipmentService, times(1)).createShipment(any(Shipment.class))
        );
    }
}
