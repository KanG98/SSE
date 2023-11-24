package com.kang98.data.dataorder.entity;

import com.kang98.data.dataproduct.entity.ProductInOrderInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Document(collection = "orders")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    private String id;

    @Field("customer_id")
    private String customerId;

    @Field("order_date")
    private Date orderDate;

    @Field("shipping_address")
    private String shippingAddress;

    @Field("total_amount")
    private Float totalAmount;

    @Field("payment_method")
    private String paymentMethod;

    @Field("order_status")
    private String orderStatus;

    @Field("scheduled_delivery")
    private Date scheduledDelivery;

    @Field("delivered_date")
    private Date deliveredDate;

    private Float discounts;

    @Field("tax_amount")
    private Float taxAmount;

    private String notes;

    private List<ProductInOrderInfo> products;

}
