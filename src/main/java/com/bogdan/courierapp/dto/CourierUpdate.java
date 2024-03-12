package com.bogdan.courierapp.dto;


import java.util.UUID;

public record CourierUpdate(
        UUID courierID,
        UUID deliveryZoneId,
        String status,
        String phoneNumber,
        Double balance
) {

}
