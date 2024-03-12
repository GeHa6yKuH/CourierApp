package com.bogdan.courierapp.service.inter;

import com.bogdan.courierapp.dto.CourierDto;
import com.bogdan.courierapp.dto.CourierUpdate;
import com.bogdan.courierapp.entity.Courier;

public interface CourierService {
    Courier getCourierById(String id);

    Courier createCourier(CourierDto courier);

    void updateCourierName(String id, String name);

    void updateCourierManager(String id, String managerId);

    void deleteById(String courierId);

    Courier updateComplexCourier(CourierUpdate courierUpdate);

    CourierDto getCourierDtoById(String id);
}
