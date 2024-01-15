package com.kang98.data.dataorder.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusDetail {

    private String orderId;

    private String orderStatus;

    private String currentLocation;

    private String statusDate;

    private String deliverymanId;
}
