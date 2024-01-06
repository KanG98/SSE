package com.kang98.service.serviceshipment.controller;

import com.kang98.service.serviceshipment.dto.GetShipmentsResponse;
import com.kang98.service.serviceshipment.service.GetShipmentsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
@RequiredArgsConstructor
@Slf4j
public class GetShipmentsController {

    private final GetShipmentsService getShipmentsService;

    @PostMapping("/shipments/all")
    public GetShipmentsResponse getAllShipments() {
        log.info("Get all shipments called at time: " + System.currentTimeMillis());
        return GetShipmentsResponse.builder().shipmentList(getShipmentsService.getAllShipments()).build();
    }
}