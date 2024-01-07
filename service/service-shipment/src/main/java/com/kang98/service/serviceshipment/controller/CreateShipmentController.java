package com.kang98.service.serviceshipment.controller;

import com.kang98.data.datashipment.entity.Shipment;
import com.kang98.service.serviceshipment.dto.CreateShipmentRequest;
import com.kang98.service.serviceshipment.service.CreateShipmentService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
public class CreateShipmentController {


    private CreateShipmentService createShipmentService;

    private ModelMapper modelMapper;

    @Autowired
    public CreateShipmentController(CreateShipmentService createShipmentService, ModelMapper modelMapper) {
        this.createShipmentService = createShipmentService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/shipments")
    @ResponseStatus(HttpStatus.CREATED)
    public void createShipment(@RequestBody @Valid CreateShipmentRequest createShipmentRequest) {
        log.info("Create shipment: " + createShipmentRequest.getOrderId() + " with delivery man " + createShipmentRequest.getDeliverymanId() + "called at time: " + System.currentTimeMillis());
        log.info("Called at time: " + System.currentTimeMillis());
        Shipment shipment = Shipment.builder()
                .orderId(createShipmentRequest.getOrderId())
                .deliverymanId(Arrays.asList(createShipmentRequest.getDeliverymanId()))
                .assignedDate(createShipmentRequest.getAssignedDate())
                .build();
        createShipmentService.createShipment(shipment);
    }
}
