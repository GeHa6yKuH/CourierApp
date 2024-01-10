package com.bogdan.courierapp.service.impl;

import com.bogdan.courierapp.entity.Courier;
import com.bogdan.courierapp.repository.TestRepository;
import com.bogdan.courierapp.service.inter.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;

    @Override
    public Courier getCourierById(String id) {
        return testRepository.getCourierById(id);
    }
}
