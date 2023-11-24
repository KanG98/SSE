package com.kang98.data.dataproduct.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductInOrderInfo {

    private String productId;

    private String productName;

    private Integer quantity;

    private Float unitPrice;
}
