package com.kang98.service.serviceproduct.service;

import com.kang98.data.dataproduct.entity.Product;
import com.kang98.data.dataproduct.repository.ProductRepository;
import com.kang98.service.serviceproduct.service.helpers.Helpers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReadProductsServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ReadProductsService readProductsService;

    @Test
    void getAllProducts_callMethod_expectedReadProductsResponse() {
        Product mockProduct  = Product.builder()
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

        var expected = Arrays.asList(mockProduct);

        when(productRepository.findAll()).thenReturn(Arrays.asList(mockProduct));
        var actual = readProductsService.getAllProducts();

        assertAll("Get all products",
                () -> assertEquals(expected, actual),
                () -> verify(productRepository, times(1)).findAll()
        );
    }
}
