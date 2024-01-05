package com.kang98.service.serviceshipment.dto;

import com.kang98.data.datashipment.entity.Shipment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetShipmentsResponse {

    private List<Shipment> shipmentList;
}
