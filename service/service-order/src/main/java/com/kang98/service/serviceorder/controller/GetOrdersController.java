package com.kang98.service.serviceorder.controller;

import com.kang98.data.dataorder.entity.OrderStatus;
import com.kang98.service.serviceorder.dto.GetOrdersResponse;
import com.kang98.service.serviceorder.dto.GetOrdersStatusesRequest;
import com.kang98.service.serviceorder.dto.GetOrdersStatusesResponse;
import com.kang98.service.serviceorder.service.GetOrdersService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class GetOrdersController {

    private final GetOrdersService getOrdersService;

    private final ModelMapper modelMapper;

    @Autowired
    GetOrdersController(GetOrdersService getOrdersService, ModelMapper modelMapper) {
        this.getOrdersService = getOrdersService;
        this.modelMapper = new ModelMapper();
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

    @PostMapping("/orders/statuses/by-order-id")
    @ResponseStatus(HttpStatus.OK)
    public GetOrdersStatusesResponse getOrderStatusesByOrderId(@RequestBody @Valid GetOrdersStatusesRequest getOrdersStatusesRequest) {
        log.info("Get order statuses by order id called: " + getOrdersStatusesRequest.getOrderId() + " at " + System.currentTimeMillis());
        OrderStatus orderStatus = getOrdersService.getOrdersStatusesByOrderId(getOrdersStatusesRequest.getOrderId());
        GetOrdersStatusesResponse getOrdersStatusesResponse = modelMapper.map(orderStatus, GetOrdersStatusesResponse.class);
        return getOrdersStatusesResponse;
    }
}
