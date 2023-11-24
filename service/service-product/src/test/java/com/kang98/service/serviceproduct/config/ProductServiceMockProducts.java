package com.kang98.service.serviceproduct.config;

import com.kang98.data.dataproduct.entity.Product;
import com.kang98.service.serviceproduct.service.helpers.Helpers;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class ProductServiceMockProducts {

    private static Product mockProduct1  = Product.builder()
            .id("5f90c393a380afd5cf0bdd1c")
            .productName("Smartphone X")
            .description("A high-performance smartphone with advanced features.")
            .category("Electronics")
            .brand("TechCo")
            .price(499.99f)
            .stockQuantity(100)
            .supplierId("5f90c393a380afd5cf0bdd1e")
            .dateAdded(Helpers.convertToDate("2023-01-05T09:15:00.000+00:00"))
            .build();

    private static Product mockProduct2  = Product.builder()
            .id("3290c39fj3d0afd5cf0bdd32")
            .productName("Laptop 12")
            .description("A high-performance laptop with advanced features.")
            .category("Electronics")
            .brand("TechCo")
            .price(499.99f)
            .stockQuantity(100)
            .supplierId("5f90c393a380afd5cf0bdd1e")
            .dateAdded(Helpers.convertToDate("2023-01-05T09:15:00.000+00:00"))
            .build();

    public static List<Product> getAllMockProducts() {
        return Arrays.asList(mockProduct1, mockProduct2);
    }

    public static Product getMockProduct1() {
        return mockProduct1;
    }

    public static Product getMockProduct2() {
        return mockProduct2;
    }
}
