package com.kang98.service.servicedeliveryman.service;

import com.kang98.data.datadeliveryman.entity.Deliveryman;
import com.kang98.data.datadeliveryman.repository.DeliverymenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateDeliverymenService {
    private final DeliverymenRepository deliverymenRepository;

    @Autowired
    public CreateDeliverymenService(DeliverymenRepository deliverymenRepository) {
        this.deliverymenRepository = deliverymenRepository;
    }

    public void createDeliveryman(Deliveryman deliveryman) {
        deliverymenRepository.save(deliveryman);
    }
}
