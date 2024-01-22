package com.bogdan.courierapp.dto;


import com.bogdan.courierapp.entity.DeliveryZone;
import com.bogdan.courierapp.entity.Statistics;
import com.bogdan.courierapp.entity.enums.Courierstatus;

import java.util.List;
import java.util.UUID;

public record CourierUpdate(
        UUID courierID,
        UUID deliveryZoneId,
        String status,
        String phoneNumber,
        Double balance
){

}
