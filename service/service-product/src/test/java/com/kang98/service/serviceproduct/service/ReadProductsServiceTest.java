package com.kang98.service.serviceproduct.service;

import com.kang98.data.dataproduct.repository.ProductRepository;
import com.kang98.service.serviceproduct.config.ProductServiceMockProducts;
import com.kang98.service.serviceproduct.dto.GetProductsRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

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
        when(productRepository.findAll()).thenReturn(ProductServiceMockProducts.getAllMockProducts());
        var expected = ProductServiceMockProducts.getAllMockProducts();
        var actual = readProductsService.getAllProducts();

        assertAll("Get all products",
                () -> assertEquals(expected, actual),
                () -> verify(productRepository, times(1)).findAll()
        );
    }

    @Test
    void getProductsByName_givenEmptyProductName_expectedAllProducts() {
        GetProductsRequest getProductsRequest = GetProductsRequest.builder().productName("").build();
        when(productRepository.findAllByName(getProductsRequest.getProductName())).thenReturn(ProductServiceMockProducts.getAllMockProducts());

        var expected = ProductServiceMockProducts.getAllMockProducts();
        var actual = readProductsService.getProductsByName(getProductsRequest.getProductName());
        assertAll("Get products by name given empty product name in request",
                () -> assertEquals(expected, actual),
                () -> verify(productRepository, times(1)).findAllByName(getProductsRequest.getProductName())
        );
    }

    @Test
    void getProductsByName_givenProductName_expectedNoProductsList() {
        GetProductsRequest getProductsRequest = GetProductsRequest.builder().productName("a product not in db").build();
        when(productRepository.findAllByName(getProductsRequest.getProductName())).thenReturn(Arrays.asList());

        var expected = Arrays.asList();
        var actual = readProductsService.getProductsByName(getProductsRequest.getProductName());
        assertAll("Get products by name given a product not in db, returns empty product list",
                () -> assertEquals(expected, actual),
                () -> verify(productRepository, times(1)).findAllByName(getProductsRequest.getProductName())
        );
    }

    @Test
    void getProductsByName_givenProductNameIgnoreCase_expectedAllProducts() {
        GetProductsRequest getProductsRequest = GetProductsRequest.builder().productName("sMartPhoNe").build();
        when(productRepository.findAllByName(getProductsRequest.getProductName())).thenReturn(Arrays.asList(ProductServiceMockProducts.getMockProduct1()));

        var expected = Arrays.asList(ProductServiceMockProducts.getMockProduct1());
        var actual = readProductsService.getProductsByName(getProductsRequest.getProductName());
        assertAll("Get products by different case name in db, returns product list",
                () -> assertEquals(expected, actual),
                () -> verify(productRepository, times(1)).findAllByName(getProductsRequest.getProductName())
        );
    }
}
