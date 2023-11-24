package com.kang98.service.serviceorder.controller;

import com.kang98.data.dataorder.entity.Order;
import com.kang98.service.serviceorder.dto.CreateOrdersRequest;
import com.kang98.service.serviceorder.service.CreateOrdersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.kang98.service.serviceorder.config.OrderServiceEndpoints.CREATE_ORDER;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CreateOrdersController {

    private final CreateOrdersService createOrdersService;

    @PostMapping(CREATE_ORDER)
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody @Valid CreateOrdersRequest createOrdersRequest) {
        log.info("Create order called: customer id = " + createOrdersRequest.getCustomerId()
                + " at " + createOrdersRequest.getOrderDate());
        createOrdersService.createOrder(Order.builder().customerId(createOrdersRequest.getCustomerId()).build());
    }
}
