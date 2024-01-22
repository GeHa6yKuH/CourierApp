package com.bogdan.courierapp.service.inter;

import com.bogdan.courierapp.dto.CourierDto;
import com.bogdan.courierapp.dto.CourierUpdate;
import com.bogdan.courierapp.entity.Courier;

public interface TestService {
    Courier getCourierById(String id);

    Courier createCourier(Courier courier);

    void updateCourierName(String id, String name);

    void deleteById(String courierId);

    Courier updateComplexCourier(CourierUpdate courierUpdate);

    CourierDto getCourierDtoById(String id);
}
