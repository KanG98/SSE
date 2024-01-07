package com.kang98.data.datashipment.repository;

import com.kang98.data.datashipment.entity.Shipment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface ShipmentsRepository extends MongoRepository<Shipment, String> {

    @Query("{ 'orderId' : ?0 }")
    public Optional<Shipment> findByOrderId(String orderId);

    @Query("{ 'assignedDate' : ?0 }")
    public Optional<Shipment> findByAssignedDate(Date assignedDate);

    @Query(value = "{ 'orderId' : ?0 }", delete = true)
    public Optional<Shipment> deleteByOrderId(String orderId);

    @Query(value = "{ 'assignedDate' : ?0 }", delete = true)
    public Optional<Shipment> deleteByAssignedDate(Date assignedDate);
}
