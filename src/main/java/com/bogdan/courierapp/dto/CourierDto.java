package com.bogdan.courierapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Value;

@Value
public class CourierDto {
    @Schema()
    String courierName;
    String phoneNumber;
}
