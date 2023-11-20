package com.kang98.service.serviceproduct.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kang98.data.dataproduct.entity.Product;
import com.kang98.data.dataproduct.repository.ProductRepository;
import com.kang98.service.serviceproduct.controller.ReadProductsController;
import com.kang98.service.serviceproduct.dto.GetAllProductsResponse;
import com.kang98.service.serviceproduct.service.helpers.Helpers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.web.format.DateTimeFormatters;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;

import static com.kang98.service.serviceproduct.config.ProductServiceTestConfig.TEST_GET_ALL_PRODUCTS_RESPONSE;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReadProductsControllerTest {
    @Mock
    private ReadProductsService readProductsService;

    @InjectMocks
    private ReadProductsController readProductsController;

    @Test
    void getAllProducts_callMethod_expectedReadProductsResponse() throws IOException {
        InputStream responseJson = this.getClass().getResourceAsStream(TEST_GET_ALL_PRODUCTS_RESPONSE);
        var getAllProductsResponse = new ObjectMapper().readValue(responseJson, GetAllProductsResponse.class);

        Product mockProduct = Product.builder()
                .id("5f90c393a380afd5cf0bdd1c")
                .productName("Smartphone X")
                .description("A high-performance smartphone with advanced features.")
                .category("Electronics")
                .brand("TechCo")
                .price(499.99)
                .stockQuantity(100)
                .supplierId("5f90c393a380afd5cf0bdd1e")
                .dateAdded(Helpers.convertToDate("2023-01-05T09:15:00.000+00:00"))
                .build();

        when(readProductsService.getAllProducts()).thenReturn(Arrays.asList(mockProduct));

        var actual = GetAllProductsResponse.builder().productList(readProductsService.getAllProducts()).build();

        assertAll("Get all products",
                () -> assertEquals(getAllProductsResponse, actual),
                () -> verify(readProductsService, times(1)).getAllProducts()
        );
    }
}
