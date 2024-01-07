package com.kang98.data.dataorder.repository;

import com.kang98.data.dataorder.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends MongoRepository<Order, String> {
    @Query(value = "{'customer_id': ?0}", delete = true)
    void deleteByCustomerId(String customerId);
}
