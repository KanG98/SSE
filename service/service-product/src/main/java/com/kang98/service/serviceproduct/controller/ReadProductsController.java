package com.kang98.service.serviceproduct.controller;

import com.kang98.data.dataproduct.entity.Product;
import com.kang98.service.serviceproduct.dto.GetAllProductsResponse;
import com.kang98.service.serviceproduct.service.ReadProductsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.kang98.service.serviceproduct.config.ProductServiceEndpoints.READ_ALL_PRODUCTS_PATH;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ReadProductsController {

    private final ReadProductsService readProductsService;

    @PostMapping(READ_ALL_PRODUCTS_PATH)
    public GetAllProductsResponse getAllProducts() {
        log.info("Get all products called");
        List<Product> productList;
        try {
            productList = readProductsService.getAllProducts();
        }
        catch (Exception e) {
            log.error("Internal Server Error", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error: " + e.getMessage(), e);
        }
        return GetAllProductsResponse.builder().productList(productList).build();
    }
}