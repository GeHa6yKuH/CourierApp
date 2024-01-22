package com.bogdan.courierapp.service.inter;

import com.bogdan.courierapp.entity.DeliveryZone;

import java.util.UUID;

public interface DeliveryZoneService {
    DeliveryZone getReferenceById(UUID id);
}
