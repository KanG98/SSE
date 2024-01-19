package com.kang98.data.dataorder.repository;

import com.kang98.data.dataorder.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersRepository extends MongoRepository<Order, String> {
    @Query(value = "{'customer_id': ?0}", delete = true)
    void deleteByCustomerId(String customerId);

    @Query(value = "{'customer_id': ?0}")
    Optional<List<Order>> findByCustomerId(String customerId);
}
