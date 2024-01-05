package com.kang98.data.datadeliveryman.repository;

import com.kang98.data.datadeliveryman.entity.Deliveryman;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliverymenRepository extends MongoRepository<Deliveryman, String> {
    @Query("{'phone_number': ?0}")
    List<Deliveryman> findAllByPhoneNumber(String phoneNumber);
}
