package com.kang98.service.serviceorder.dto;

import com.kang98.data.dataorder.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOrdersResponse {

    private List<Order> orders;
}
