package com.kang98.service.serviceshipment.service;

import com.kang98.data.datashipment.entity.Shipment;
import com.kang98.data.datashipment.repository.ShipmentsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateShipmentServiceTest {

    @Mock
    private ShipmentsRepository shipmentsRepository;

    @InjectMocks
    private CreateShipmentService createShipmentService;

    @Test
    void createShipment_callMethod_expectedSuccess() {
        Shipment mockShipment = Shipment.builder().orderId("0").build();
        when(shipmentsRepository.save(any(Shipment.class))).thenReturn(mockShipment);
        assertAll("Create shipment",
                () -> assertEquals(createShipmentService.createShipment(mockShipment), mockShipment),
                () -> verify(shipmentsRepository, times(1)).save(any(Shipment.class))
        );
    }
}
