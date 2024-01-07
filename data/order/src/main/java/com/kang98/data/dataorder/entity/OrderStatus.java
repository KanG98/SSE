package com.kang98.data.dataorder.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatus {

    private String orderId;

    private String orderStatus;

    private String currentLocation;

    private String statusDate;

    private String deliverymanId;
}
