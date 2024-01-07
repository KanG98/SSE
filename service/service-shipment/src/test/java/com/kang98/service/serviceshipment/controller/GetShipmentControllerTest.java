package com.kang98.service.serviceshipment.controller;

import com.kang98.data.datashipment.entity.Shipment;
import com.kang98.service.serviceshipment.dto.GetShipmentsResponse;
import com.kang98.service.serviceshipment.service.GetShipmentsService;
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
public class GetShipmentControllerTest {

    @Mock
    private GetShipmentsService getShipmentsService;

    @InjectMocks
    private GetShipmentsController getShipmentsController;

    @Test
    void getAllShipments_callMethod_expectedShipmentList() {
        Shipment mockShipment = Shipment.builder().orderId("id").build();
        when(getShipmentsService.getAllShipments()).thenReturn(Arrays.asList(mockShipment));

        GetShipmentsResponse getShipmentsResponse = GetShipmentsResponse.builder().shipmentList(Arrays.asList(mockShipment)).build();

        assertAll("get all shipments",
                () -> assertEquals(getShipmentsController.getAllShipments(), getShipmentsResponse),
                () -> verify(getShipmentsService, times(1)).getAllShipments()
        );
    }
}
