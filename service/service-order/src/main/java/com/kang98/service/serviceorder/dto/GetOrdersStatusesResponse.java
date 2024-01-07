package com.kang98.service.serviceorder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetOrdersStatusesResponse {

    private String orderId;

    private String orderStatus;

    private String currentLocation;

    private String statusDate;

    private String deliverymanId;
}
