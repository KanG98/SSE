package com.kang98.service.servicedeliveryman.controller;

import com.kang98.data.datadeliveryman.entity.Deliveryman;
import com.kang98.service.servicedeliveryman.dto.GetDeliverymanByPhoneNumberRequest;
import com.kang98.service.servicedeliveryman.service.GetDeliverymanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetDeliverymenController {

    @Autowired
    private GetDeliverymanService getDeliverymanService;

    @PostMapping("/deliveryman/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Deliveryman> getAllDeliveryman() {
        return getDeliverymanService.getAllProducts();
    }

    @PostMapping("/deliveryman/by-phone-number")
    @ResponseStatus(HttpStatus.OK)
    public List<Deliveryman> getDeliverymanByPhoneNumber(@RequestBody @Valid GetDeliverymanByPhoneNumberRequest getDeliverymanByPhoneNumberRequest) {
        return getDeliverymanService.getAllProductsByPhoneNumber(getDeliverymanByPhoneNumberRequest.getPhoneNumber());
    }

}
