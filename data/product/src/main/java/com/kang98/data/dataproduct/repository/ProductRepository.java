package com.kang98.data.dataproduct.repository;

import com.kang98.data.dataproduct.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    @Query("{'product_name': { $regex: ?0, $options: 'i' }}")
    List<Product> findAllByName(String productName);
}
