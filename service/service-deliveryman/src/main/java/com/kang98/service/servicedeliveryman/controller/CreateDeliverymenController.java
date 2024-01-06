package com.kang98.service.servicedeliveryman.controller;

import com.kang98.data.datadeliveryman.entity.Deliveryman;
import com.kang98.service.servicedeliveryman.dto.CreateDeliverymanRequest;
import com.kang98.service.servicedeliveryman.service.CreateDeliverymenService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class CreateDeliverymenController {

    private CreateDeliverymenService createDeliverymenService;

    private ModelMapper mapper;

    @Autowired
    CreateDeliverymenController(CreateDeliverymenService createDeliverymenService, ModelMapper mapper) {
        this.createDeliverymenService = createDeliverymenService;
        this.mapper = mapper;
    }

    @PostMapping("/deliveryman")
    @ResponseStatus(HttpStatus.CREATED)
    public void createDeliveryman(@RequestBody @Valid CreateDeliverymanRequest createDeliverymanRequest) {
        log.info("Create deliveryman called: email = " + createDeliverymanRequest.getEmail());
        Deliveryman deliveryman = mapper.map(createDeliverymanRequest, Deliveryman.class);
        createDeliverymenService.createDeliveryman(deliveryman);
    }
}
