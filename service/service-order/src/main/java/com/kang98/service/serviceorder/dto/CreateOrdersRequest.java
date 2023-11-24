package com.kang98.service.serviceorder.dto;

import com.kang98.data.dataproduct.entity.ProductInOrderInfo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrdersRequest {

    @NotNull
    private String customerId;

    @NotNull
    @Past(message = "Order date must be in the past")
    private Date orderDate;

    @NotBlank
    private String shippingAddress;

    @Positive
    private Float totalAmount;

    @NotBlank
    private String paymentMethod;

    @NotBlank
    private String orderStatus;

    @NotNull
    private Date deliveredDate;

    @NotNull
    private Date scheduledDelivery;

    @PositiveOrZero
    private Float discounts;

    @PositiveOrZero
    private Float taxAmount;

    @NotBlank
    private String notes;

    @NotNull
    private List<@Valid ProductInOrderInfo> products;
}
