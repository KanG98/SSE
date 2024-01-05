package com.kang98.service.serviceshipment.service;

import com.kang98.data.datashipment.entity.Shipment;
import com.kang98.data.datashipment.repository.ShipmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetShipmentsService {

    @Autowired
    private ShipmentsRepository shipmentsRepository;

    public List<Shipment> getAllShipments() {
        return shipmentsRepository.findAll();
    }
}
