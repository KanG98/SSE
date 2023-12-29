package com.kang98.service.servicedeliveryman.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetDeliverymenController {

    @PostMapping("/deliveryman/all")
    @ResponseStatus(HttpStatus.OK)
    public String getAllDeliveryman() {
        return "hi";

    }

}
