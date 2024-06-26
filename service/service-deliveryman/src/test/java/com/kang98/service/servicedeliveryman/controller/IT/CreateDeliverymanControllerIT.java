package com.kang98.service.servicedeliveryman.controller.IT;

import com.kang98.data.datadeliveryman.entity.Deliveryman;
import com.kang98.data.datadeliveryman.repository.DeliverymenRepository;
import com.kang98.service.servicedeliveryman.config.TestDeliverymanConfig;
import com.kang98.service.servicedeliveryman.controller.CreateDeliverymanController;
import com.kang98.service.servicedeliveryman.dto.CreateDeliverymanRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Date;

@SpringBootTest
public class CreateDeliverymanControllerIT {

    @Autowired
    private CreateDeliverymanController createDeliverymanController;

    @Autowired
    private DeliverymenRepository deliverymenRepository;

    @Test
    void createDeliveryman_callMethod_expectedSuccess() {
        CreateDeliverymanRequest createDeliverymanRequest = CreateDeliverymanRequest.builder().email("IT").build();
        createDeliverymanController.createDeliveryman(createDeliverymanRequest);
        assert deliverymenRepository.findAll().stream().anyMatch(dm -> dm.getEmail().equals("IT")) == true;
        deliverymenRepository.deleteByEmail("IT");
    }
}
