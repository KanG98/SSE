package com.kang98.service.servicedeliveryman.dto;

import com.kang98.data.datadeliveryman.entity.Deliveryman;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetDeliverymanResponse {
    private List<Deliveryman> deliverymanList;
}
