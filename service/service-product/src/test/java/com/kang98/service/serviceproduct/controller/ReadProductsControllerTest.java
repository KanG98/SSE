package com.kang98.service.serviceproduct.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kang98.service.serviceproduct.config.ProductServiceMockProducts;
import com.kang98.service.serviceproduct.controller.ReadProductsController;
import com.kang98.service.serviceproduct.dto.GetProductsRequest;
import com.kang98.service.serviceproduct.dto.GetProductsResponse;
import com.kang98.service.serviceproduct.service.ReadProductsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import static com.kang98.service.serviceproduct.config.ProductServiceTestConfig.TEST_GET_PRODUCTS_REQUEST;
import static com.kang98.service.serviceproduct.config.ProductServiceTestConfig.TEST_GET_PRODUCTS_RESPONSE;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReadProductsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private ReadProductsService readProductsService;

    @InjectMocks
    private ReadProductsController readProductsController;

    @Test
    void getAllProducts_callMethod_expectedReadProductsResponse() throws IOException {
        InputStream responseJson = this.getClass().getResourceAsStream(TEST_GET_PRODUCTS_RESPONSE);
        var getAllProductsResponse = new ObjectMapper().readValue(responseJson, GetProductsResponse.class);

        when(readProductsService.getAllProducts()).thenReturn(Arrays.asList(ProductServiceMockProducts.getMockProduct1()));

        var actual = GetProductsResponse.builder().productList(readProductsService.getAllProducts()).build();

        assertAll("Get all products",
                () -> assertEquals(getAllProductsResponse, actual),
                () -> verify(readProductsService, times(1)).getAllProducts()
        );
    }

    @Test
    void getProductsByName_givenEmptyProductName_expectedAllProducts() throws IOException {
        GetProductsRequest getProductsRequest = GetProductsRequest.builder().productName("").build();
        GetProductsResponse getAllProductsResponse = GetProductsResponse.builder().productList(ProductServiceMockProducts.getAllMockProducts()).build();

        when(readProductsService.getProductsByName(getProductsRequest.getProductName())).thenReturn(ProductServiceMockProducts.getAllMockProducts());

        var actual = readProductsController.getProductsByName(getProductsRequest);
        assertAll("Get products by name given empty product name in request",
                () -> assertEquals(getAllProductsResponse, actual),
                () -> verify(readProductsService, times(1)).getProductsByName(getProductsRequest.getProductName())
        );
    }

    @Test
    void getProductsByName_givenProductName_expectedNoProductsList() throws IOException {
        GetProductsRequest getProductsRequest = GetProductsRequest.builder().productName("does not exist").build();

        when(readProductsService.getProductsByName(getProductsRequest.getProductName())).thenReturn(Arrays.asList());

        var expected = GetProductsResponse.builder().productList(Arrays.asList()).build();
        var actual = readProductsController.getProductsByName(getProductsRequest);
        assertAll("Get products by name not exist in db",
                () -> assertEquals(expected, actual),
                () -> verify(readProductsService, times(1)).getProductsByName(getProductsRequest.getProductName())
        );
    }
    @Test
    void getProductsByName_givenProductNameIgnoreCase_expectedAllProducts() throws IOException {
        InputStream requestJson = this.getClass().getResourceAsStream(TEST_GET_PRODUCTS_REQUEST);
        var getProductsRequest = new ObjectMapper().readValue(requestJson, GetProductsRequest.class);
        InputStream responseJson = this.getClass().getResourceAsStream(TEST_GET_PRODUCTS_RESPONSE);
        var getAllProductsResponse = new ObjectMapper().readValue(responseJson, GetProductsResponse.class);

        when(readProductsService.getProductsByName(getProductsRequest.getProductName())).thenReturn(Arrays.asList(ProductServiceMockProducts.getMockProduct1()));

        var actual = readProductsController.getProductsByName(getProductsRequest);
        assertAll("Get products by name given ignore case sensitive names",
                () -> assertEquals(getAllProductsResponse, actual),
                () -> verify(readProductsService, times(1)).getProductsByName(getProductsRequest.getProductName())
        );
    }
}
