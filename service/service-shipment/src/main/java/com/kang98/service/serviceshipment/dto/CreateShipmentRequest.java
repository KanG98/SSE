package com.kang98.service.serviceshipment.dto;

import com.kang98.data.datashipment.entity.Shipment;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateShipmentRequest {

    @NotNull
    private String orderId;

    @NotNull
    private String deliverymanId;

    @NotNull
    private Date assignedDate;
}
