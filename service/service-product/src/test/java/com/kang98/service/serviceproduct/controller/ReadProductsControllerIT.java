package com.kang98.service.serviceproduct.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kang98.data.dataproduct.repository.ProductsRepository;
import com.kang98.service.serviceproduct.config.ProductServiceConfig;
import com.kang98.service.serviceproduct.dto.GetProductsRequest;
import com.kang98.service.serviceproduct.dto.GetProductsResponse;
import com.kang98.service.serviceproduct.service.ReadProductsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;

import static com.kang98.service.serviceproduct.config.ProductServiceTestConfig.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes={ProductServiceConfig.class})
public class ReadProductsControllerIT {

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private ReadProductsService readProductsService;

    @Autowired
    private ReadProductsController readProductsController;

    @Test
    void getAllProducts_callMethod_expectedReadProductsResponse() throws IOException {
        InputStream responseJson = this.getClass().getResourceAsStream(TEST_GET_ALL_PRODUCTS_RESPONSE);
        var getAllProductsResponse = new ObjectMapper().readValue(responseJson, GetProductsResponse.class);

        var actual = readProductsController.getAllProducts();

        assertAll("Get all products",
                () -> assertEquals(getAllProductsResponse, actual)
        );
    }

    @Test
    void getProductsByName_givenEmptyProductName_expectedAllProducts() throws IOException {
        GetProductsRequest getProductsRequest = GetProductsRequest.builder().productName("").build();
        InputStream responseJson = this.getClass().getResourceAsStream(TEST_GET_ALL_PRODUCTS_RESPONSE);
        var getAllProductsResponse = new ObjectMapper().readValue(responseJson, GetProductsResponse.class);

        var actual = readProductsController.getProductsByName(getProductsRequest);
        assertAll("Get products by name given empty product name in request",
                () -> assertEquals(getAllProductsResponse, actual)
        );
    }

    @Test
    void getProductsByName_givenProductName_expectedNoProductsList() {
        GetProductsRequest getProductsRequest = GetProductsRequest.builder().productName("does not exist").build();

        var actual = readProductsController.getProductsByName(getProductsRequest);
        assertAll("Get products by name not exist in db",
                () -> assertEquals(0, actual.getProductList().size())
        );
    }

    @Test
    void getProductsByName_givenProductNameIgnoreCase_expectedAllProducts() throws IOException {
        InputStream requestJson = this.getClass().getResourceAsStream(TEST_GET_PRODUCTS_REQUEST);
        var getProductsRequest = new ObjectMapper().readValue(requestJson, GetProductsRequest.class);
        InputStream responseJson = this.getClass().getResourceAsStream(TEST_GET_ALL_PRODUCTS_RESPONSE);
        var getAllProductsResponse = new ObjectMapper().readValue(responseJson, GetProductsResponse.class);

        var actual = readProductsController.getProductsByName(getProductsRequest);
        assertAll("Get products by name given ignore case sensitive names",
                () -> assertEquals(getAllProductsResponse, actual)
        );
    }
}
