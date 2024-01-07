package com.kang98.service.serviceshipment.service;

import com.kang98.data.datashipment.entity.Shipment;
import com.kang98.data.datashipment.repository.ShipmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreateShipmentService {

    private ShipmentsRepository shipmentsRepository;

    @Autowired
    public CreateShipmentService(ShipmentsRepository shipmentsRepository) {
        this.shipmentsRepository = shipmentsRepository;
    }

    public Shipment createShipment(Shipment shipment) {
        Optional<Shipment> shipmentInDB = shipmentsRepository.findByOrderId(shipment.getOrderId());
        if (shipmentInDB.isPresent()) {
            // add new deliveryman id
            List<String> newDeliverymanId = shipment.getDeliverymanId();
            List<String> currentDeliverymanIds = shipmentInDB.get().getDeliverymanId();
            currentDeliverymanIds.addAll(newDeliverymanId);
            shipmentInDB.get().setDeliverymanId(currentDeliverymanIds);
            // update assigned date
            shipmentInDB.get().setAssignedDate(shipment.getAssignedDate());
            // update database
            shipmentsRepository.deleteByOrderId(shipment.getOrderId());
            return shipmentsRepository.save(shipmentInDB.get());
        } else {
            Shipment newShipment = Shipment.builder()
                    .orderId(shipment.getOrderId())
                    .deliverymanId(shipment.getDeliverymanId())
                    .assignedDate(shipment.getAssignedDate())
                    .build();
            return shipmentsRepository.save(newShipment);
        }
    }
}
