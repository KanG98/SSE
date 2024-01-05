package com.kang98.service.serviceshipment.service;

import com.kang98.data.datashipment.entity.Shipment;
import com.kang98.data.datashipment.repository.ShipmentsRepository;
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
public class GetShipmentServiceTest {

    @Mock
    private ShipmentsRepository shipmentsRepository;

    @InjectMocks
    private GetShipmentsService getShipmentsService;

    @Test
    void getAllShipments_callMethod_expectedShipmentList() {
        Shipment mockShipment = Shipment.builder().id("id").build();
        when(shipmentsRepository.findAll()).thenReturn(Arrays.asList(mockShipment));

        assertAll("Get all shipments list",
                () -> assertEquals(getShipmentsService.getAllShipments(), Arrays.asList(mockShipment)),
                () -> verify(shipmentsRepository, times(1)).findAll()
        );
    }

}
