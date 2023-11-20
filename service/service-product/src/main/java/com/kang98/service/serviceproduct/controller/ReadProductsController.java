package com.kang98.service.serviceproduct.controller;

import com.kang98.data.dataproduct.entity.Product;
import com.kang98.service.serviceproduct.service.ReadProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.kang98.service.serviceproduct.config.ProductServiceEndpoints.READ_ALL_PRODUCTS_PATH;

@RestController
@RequiredArgsConstructor
public class ReadProductsController {

    private final ReadProductService readProductService;

    @PostMapping(READ_ALL_PRODUCTS_PATH)
    public List<Product> getAllProducts() {
        return readProductService.getAllProducts();
    }
}