package com.kang98.data.datashipment.repository;

import com.kang98.data.datashipment.entity.Shipment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentsRepository extends MongoRepository<Shipment, String> {
}
