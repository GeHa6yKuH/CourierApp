package com.bogdan.courierapp.service.impl;

import com.bogdan.courierapp.dto.CourierDto;
import com.bogdan.courierapp.dto.CourierUpdate;
import com.bogdan.courierapp.entity.Courier;
import com.bogdan.courierapp.entity.DeliveryZone;
import com.bogdan.courierapp.entity.enums.Courierstatus;
import com.bogdan.courierapp.exception.CourierNotFoundException;
import com.bogdan.courierapp.mapper.CourierMapper;
import com.bogdan.courierapp.repository.CourierRepository;
import com.bogdan.courierapp.repository.DeliveryZoneRepository;
import com.bogdan.courierapp.service.inter.DeliveryZoneService;
import com.bogdan.courierapp.service.inter.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourierServiceImpl implements CourierService {

    private final DeliveryZoneService deliveryZoneService;

    private final CourierRepository courierRepository;

    private final CourierMapper courierMapper;

    private final DeliveryZoneRepository deliveryZoneRepository;

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Courier getCourierById(String id) {
        return courierRepository.findCourierById(UUID.fromString(id));
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Courier createCourier(CourierDto courier) {
        DeliveryZone deliveryZone = deliveryZoneRepository.findById(courier.getDeliveryZoneId())
                .orElseThrow(() -> new RuntimeException("No such delivery zone!"));
        Courier courier1 = Courier.builder()
                .deliveryZone(deliveryZone)
                .balance(0)
                .courierName(courier.getCourierName())
                .phoneNumber(courier.getPhoneNumber())
                .status(Courierstatus.OFFLINE)
                .registrationDate(new Date(System.currentTimeMillis()))
                .build();
        return courierRepository.save(courier1);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void updateCourierName(String id, String name) {
        courierRepository.updateCourierName(UUID.fromString(id), name);
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public CourierDto getCourierDtoById(String id) {
        return courierMapper.toDto(courierRepository
                .findById(UUID.fromString(id)).orElseThrow(() ->
                        new CourierNotFoundException("Courier with id: " + " not found")));
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void deleteById(String courierId) {
        courierRepository.deleteById(UUID.fromString(courierId));
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Courier updateComplexCourier(CourierUpdate courierUpdate) {
        Courier courier = courierRepository.findById(courierUpdate.courierID())
                .orElseThrow(() -> new CourierNotFoundException("Courier with id: " + courierUpdate.courierID() + " not found"));
        courier.setDeliveryZone(deliveryZoneService.getReferenceById(courierUpdate.deliveryZoneId()))
                .setStatus(Courierstatus.valueOf(courierUpdate.status()))
                .setPhoneNumber(courierUpdate.phoneNumber())
                .setBalance(courierUpdate.balance());
        // todo: make Validation
        return courierRepository.saveAndFlush(courier);
    }
}
