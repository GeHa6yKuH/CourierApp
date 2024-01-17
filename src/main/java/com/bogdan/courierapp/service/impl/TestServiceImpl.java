package com.bogdan.courierapp.service.impl;

import com.bogdan.courierapp.entity.Courier;
import com.bogdan.courierapp.repository.TestRepository;
import com.bogdan.courierapp.service.inter.TestService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;

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
    @Transactional
    public void deleteById(String courierId) {
        testRepository.deleteById(UUID.fromString(courierId));
    }
}
