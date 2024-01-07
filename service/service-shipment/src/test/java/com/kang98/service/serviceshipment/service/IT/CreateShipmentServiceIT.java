package com.kang98.service.serviceshipment.service.IT;

import com.kang98.data.datashipment.entity.Shipment;
import com.kang98.data.datashipment.repository.ShipmentsRepository;
import com.kang98.service.serviceshipment.dto.CreateShipmentRequest;
import com.kang98.service.serviceshipment.service.CreateShipmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class CreateShipmentServiceIT {

    @Autowired
    private CreateShipmentService createShipmentService;

    @Autowired
    private ShipmentsRepository shipmentsRepository;

    @Test
    void createShipment_callMethod_expectedSuccess() {
        Shipment shipment = Shipment.builder()
                .orderId("0")
                .deliverymanId(Arrays.asList("0"))
                .build();

        // create once
        createShipmentService.createShipment(shipment);
        assert shipmentsRepository.findByOrderId("0").isPresent();

        // create twice
        createShipmentService.createShipment(shipment);
        assert shipmentsRepository.findByOrderId("0").isPresent();
        assert shipmentsRepository.findByOrderId("0").get().getDeliverymanId().size() > 1;

        // clean up
        shipmentsRepository.deleteByOrderId("0");
    }

}
