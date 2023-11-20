package com.kang98.data.dataproduct.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "products")
@Data
public class Product {
    @Id
    private String id;

    @Field("product_name")
    private String productName;

    private String description;

    private String category;

    private String brand;

    private double price;

    @Field("stock_quantity")
    private int stockQuantity;

    @Field("supplier_id")
    private String supplierId;

    @Field("date_added")
    private Date dateAdded;
}
