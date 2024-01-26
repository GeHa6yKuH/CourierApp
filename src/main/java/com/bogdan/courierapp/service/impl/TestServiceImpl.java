package com.bogdan.courierapp.service.impl;

import com.bogdan.courierapp.dto.CourierDto;
import com.bogdan.courierapp.dto.CourierUpdate;
import com.bogdan.courierapp.entity.Courier;
import com.bogdan.courierapp.entity.enums.Courierstatus;
import com.bogdan.courierapp.exception.CourierNotFoundException;
import com.bogdan.courierapp.mapper.CourierMapper;
import com.bogdan.courierapp.repository.TestRepository;
import com.bogdan.courierapp.service.inter.DeliveryZoneService;
import com.bogdan.courierapp.service.inter.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final DeliveryZoneService deliveryZoneService;

    private final TestRepository testRepository;

    private final CourierMapper courierMapper;

    @Override
    public Courier getCourierById(String id) {
        return testRepository.findCourierById(UUID.fromString(id));
    }

    @Override
    @Transactional
    public Courier createCourier(Courier courier) {
        return testRepository.save(courier);
    }

    @Override
    @Transactional
    public void updateCourierName(String id, String name) {
        testRepository.updateCourierName(UUID.fromString(id), name);
    }

    @Override
    public CourierDto getCourierDtoById(String id) {
        return courierMapper.toDto(testRepository.findById(UUID.fromString(id)).orElseThrow(() -> new CourierNotFoundException("Courier with id: " + " not found")));
    }

    @Override
//    @Transactional
    public void deleteById(String courierId) {
        testRepository.deleteById(UUID.fromString(courierId));
    }

    @Override
    @Transactional
    public Courier updateComplexCourier(CourierUpdate courierUpdate) {
        Courier courier = testRepository.findById(courierUpdate.courierID()).orElseThrow(() -> new CourierNotFoundException("Courier with id: " + courierUpdate.courierID() + " not found"));
        courier.setDeliveryZone(deliveryZoneService.getReferenceById(courierUpdate.deliveryZoneId()))
                .setStatus(Courierstatus.valueOf(courierUpdate.status()))
                .setPhoneNumber(courierUpdate.phoneNumber())
                .setBalance(courierUpdate.balance());
        // todo: make Validation
        return testRepository.saveAndFlush(courier);
    }
}
