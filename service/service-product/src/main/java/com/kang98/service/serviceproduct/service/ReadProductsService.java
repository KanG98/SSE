package com.kang98.service.serviceproduct.service;

import com.kang98.data.dataproduct.entity.Product;
import com.kang98.data.dataproduct.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReadProductsService {

    private final ProductsRepository productsRepository;

    public List<Product> getAllProducts() {
        return productsRepository.findAll();
    }

    public List<Product> getProductsByName(String productName) {
        return productsRepository.findAllByName(productName);
    }
}
