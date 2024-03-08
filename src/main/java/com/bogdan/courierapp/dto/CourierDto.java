package com.bogdan.courierapp.dto;

import com.bogdan.courierapp.entity.DeliveryZone;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Value;

import java.util.UUID;

@Value
public class CourierDto {
    @Schema()
    String courierName;
    String phoneNumber;
    UUID deliveryZoneId;
}
