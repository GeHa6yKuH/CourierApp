package com.bogdan.courierapp.dto;

import com.bogdan.courierapp.entity.enums.OrderStatus;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;
@Value
public class OrderDto {
    UUID restaurantId;
    OrderStatus status;
}
