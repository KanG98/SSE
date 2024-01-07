package com.kang98.service.serviceshipment.controller.IT;

import com.kang98.data.datashipment.repository.ShipmentsRepository;
import com.kang98.service.serviceshipment.controller.CreateShipmentController;
import com.kang98.service.serviceshipment.dto.CreateShipmentRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class CreateShipmentControllerIT {

    @Autowired
    private CreateShipmentController createShipmentController;

    @Autowired
    private ShipmentsRepository shipmentsRepository;

    @Test
    void createShipment_createTwice_expectedSuccess() {
        CreateShipmentRequest createShipmentRequest = CreateShipmentRequest.builder()
                .orderId("0")
                .deliverymanId("0")
                .build();

        // create onece
        createShipmentController.createShipment(createShipmentRequest);
        assert shipmentsRepository.findByOrderId("0").isPresent();

        // create twice
        createShipmentController.createShipment(createShipmentRequest);
        assert shipmentsRepository.findByOrderId("0").isPresent();
        assert shipmentsRepository.findByOrderId("0").get().getDeliverymanId().size() > 1;

        // clean up
        shipmentsRepository.deleteByOrderId("0");
    }
}
