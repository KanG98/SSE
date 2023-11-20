package com.kang98.service.serviceproduct.dto;

import com.kang98.data.dataproduct.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetProductsResponse {
    private List<Product> productList;
}
