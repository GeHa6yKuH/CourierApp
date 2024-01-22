package com.bogdan.courierapp.service.impl;

import com.bogdan.courierapp.entity.DeliveryZone;
import com.bogdan.courierapp.repository.DeliveryZoneRepository;
import com.bogdan.courierapp.service.inter.DeliveryZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class DeliveryZoneServiceImpl implements DeliveryZoneService {

    private final DeliveryZoneRepository deliveryZoneRepository;
    @Override
    public DeliveryZone getReferenceById(UUID id) {
        return deliveryZoneRepository.getReferenceById(id);
    }
}
