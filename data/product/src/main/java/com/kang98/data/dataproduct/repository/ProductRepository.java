package com.kang98.data.dataproduct.repository;

import com.kang98.data.dataproduct.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
