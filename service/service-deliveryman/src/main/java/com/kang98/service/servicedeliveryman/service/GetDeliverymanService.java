package com.kang98.service.servicedeliveryman.service;

import com.kang98.data.datadeliveryman.entity.Deliveryman;
import com.kang98.data.datadeliveryman.repository.DeliverymenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetDeliverymanService {

    @Autowired
    private DeliverymenRepository deliverymenRepository;

    public List<Deliveryman> getAllDeliverymen() {
        return deliverymenRepository.findAll();
    }

    public List<Deliveryman> getAllDeliverymanByPhoneNumber(String phoneNumber) {
        return deliverymenRepository.findAllByPhoneNumber(phoneNumber);
    }
}
