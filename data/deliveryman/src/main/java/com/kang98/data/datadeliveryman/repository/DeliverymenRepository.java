package com.kang98.data.datadeliveryman.repository;

import com.kang98.data.datadeliveryman.entity.Deliveryman;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface DeliverymenRepository extends MongoRepository<Deliveryman, String> {

    @Query("{'phone_number': ?0}")
    List<Deliveryman> findAllByPhoneNumber(String phoneNumber);

    // this is mainly for testing purposes
    @Query(value = "{'email': ?0}", delete = true)
    void deleteByEmail(String email);

    @Query(value = "{'since_date': ?0}", delete = true)
    void deleteBySinceDate(Date sinceDate);
}
