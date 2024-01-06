package com.kang98.service.serviceorder.controller;

import com.kang98.service.serviceorder.dto.GetOrdersResponse;
import com.kang98.service.serviceorder.service.GetOrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class GetOrdersController {

    private final GetOrdersService getOrdersService;

    @Autowired
    GetOrdersController(GetOrdersService getOrdersService) {
        this.getOrdersService = getOrdersService;
    }

    @PostMapping("/orders/all")
    @ResponseStatus(HttpStatus.OK)
    public GetOrdersResponse getAllOrders() {
        log.info("Get all orders called");
        GetOrdersResponse getOrdersResponse = GetOrdersResponse.builder()
                .orders(getOrdersService.getAllOrders())
                .build();
        return getOrdersResponse;
    }
}
