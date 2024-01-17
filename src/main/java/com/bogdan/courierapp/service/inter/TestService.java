package com.bogdan.courierapp.service.inter;

import com.bogdan.courierapp.entity.Courier;

import java.util.UUID;

public interface TestService {
    Courier getCourierById(String id);

    Courier createCourier(Courier courier);

    void updateCourierName(String id, String name);

    void deleteById(String courierId);
}
