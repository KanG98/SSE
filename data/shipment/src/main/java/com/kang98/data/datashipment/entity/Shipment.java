package com.kang98.data.datashipment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "shipments")
public class Shipment {

    @Id
    private String id;

    @Field("order_id")
    private String orderId;

    @Field("deliveryman_id")
    private List<String> deliverymanId;

    @Field("assigned_date")
    private Date assignedDate;
}
