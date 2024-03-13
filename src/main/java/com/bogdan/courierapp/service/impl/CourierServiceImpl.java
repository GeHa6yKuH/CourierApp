package com.bogdan.courierapp.service.impl;

import com.bogdan.courierapp.dto.CourierDto;
import com.bogdan.courierapp.dto.CourierUpdate;
import com.bogdan.courierapp.entity.Courier;
import com.bogdan.courierapp.entity.DeliveryZone;
import com.bogdan.courierapp.entity.enums.Courierstatus;
import com.bogdan.courierapp.exception.CourierNotFoundException;
import com.bogdan.courierapp.exception.DeliveryZoneNotFoundException;
import com.bogdan.courierapp.exception.ErrorMessage;
import com.bogdan.courierapp.exception.SupportManagerException;
import com.bogdan.courierapp.mapper.CourierMapper;
import com.bogdan.courierapp.repository.CourierRepository;
import com.bogdan.courierapp.repository.DeliveryZoneRepository;
import com.bogdan.courierapp.repository.SupportManagerRepository;
import com.bogdan.courierapp.service.inter.CourierService;
import com.bogdan.courierapp.service.inter.DeliveryZoneService;
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
    private final SupportManagerRepository supportManagerRepository;

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Courier getCourierById(String id) {
        return courierRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new CourierNotFoundException(ErrorMessage.COURIER_NOT_FOUND));
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Courier createCourier(CourierDto courier) {
        DeliveryZone deliveryZone = deliveryZoneRepository.findById(courier.getDeliveryZoneId())
                .orElseThrow(() -> new DeliveryZoneNotFoundException(ErrorMessage.DELIVERY_ZONE_NOT_FOUND));
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
        getCourierById(id);
        courierRepository.updateCourierName(UUID.fromString(id), name);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public void updateCourierManager(String id, String managerId) {
        getCourierById(id);
        supportManagerRepository.findById(UUID.fromString(managerId))
                .orElseThrow(() -> new SupportManagerException(ErrorMessage.SUPPORT_MANAGER_NOT_FOUND));
        courierRepository.updateCourierManager(UUID.fromString(id), UUID.fromString(managerId));
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public CourierDto getCourierDtoById(String id) {
        return courierMapper.toDto(courierRepository
                .findById(UUID.fromString(id)).orElseThrow(() ->
                        new CourierNotFoundException("Courier with id: " + " not found")));
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Override
    public void deleteById(String courierId) {
        getCourierById(courierId);
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
        return courierRepository.saveAndFlush(courier);
    }
}
