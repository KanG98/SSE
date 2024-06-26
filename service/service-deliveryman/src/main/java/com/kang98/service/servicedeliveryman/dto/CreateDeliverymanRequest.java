package com.kang98.service.servicedeliveryman.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Builder
@Data
public class CreateDeliverymanRequest {

    @NotNull
    @Field("first_name")
    private String firstName;

    @NotNull
    @Field("last_name")
    private String lastName;

    @NotNull
    @Field("phone_number")
    private String phoneNumber;

    @NotNull
    @Field("email")
    private String email;

    @NotNull
    @Field("since_date")
    private Date sinceDate;

    @Field("current_location")
    private String currentLocation;
}
