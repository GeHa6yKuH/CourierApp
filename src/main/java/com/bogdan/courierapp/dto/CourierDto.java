package com.bogdan.courierapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.util.UUID;

@Value
public class CourierDto {
    @Schema()
    String courierName;
    String phoneNumber;
    UUID deliveryZoneId;
}
