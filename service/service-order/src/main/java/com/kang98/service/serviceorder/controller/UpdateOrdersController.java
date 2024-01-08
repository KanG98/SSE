package com.kang98.service.serviceorder.controller;

import com.kang98.foundation.helper.Helpers;
import com.kang98.service.serviceorder.dto.UpdateOrderStatusRequest;
import com.kang98.service.serviceorder.dto.UpdateOrderStatusResponse;
import com.kang98.service.serviceorder.service.GetOrdersService;
import com.kang98.service.serviceorder.service.UpdateOrdersService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UpdateOrdersController {

    private final UpdateOrdersService updateOrdersService;


    @Autowired
    public UpdateOrdersController(UpdateOrdersService updateOrdersService) {
        this.updateOrdersService = updateOrdersService;
    }

    @PostMapping("/orders/statuses")
    @ResponseStatus(HttpStatus.OK)
    public UpdateOrderStatusResponse updateOrderStatus(@RequestBody @Valid UpdateOrderStatusRequest updateOrderStatusRequest) {
        String orderId = updateOrderStatusRequest.getOrderId();
        String newOrderStatus = updateOrderStatusRequest.getOrderStatus();

        boolean isDBAcknowledged = updateOrdersService.updateOrderStatus(orderId, newOrderStatus);

        // todo: search order id first, if not found, return 204
        UpdateOrderStatusResponse updateOrderStatusResponse = UpdateOrderStatusResponse.builder()
                .dbAcknowledgement(isDBAcknowledged)
                .updatedAt(Helpers.getCurrentISODate())
                .build();
        log.info("updateOrderStatusResponse: {}", updateOrderStatusResponse);
        return updateOrderStatusResponse;
    }
}
