package com.kang98.data.datadeliveryman.repository;

import com.kang98.data.datadeliveryman.entity.Deliveryman;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliverymenRepository extends MongoRepository<Deliveryman, String> {
}
