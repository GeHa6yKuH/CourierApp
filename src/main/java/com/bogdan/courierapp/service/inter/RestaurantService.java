package com.bogdan.courierapp.service.inter;

import com.bogdan.courierapp.dto.RestaurantDto;
import com.bogdan.courierapp.entity.Courier;
import com.bogdan.courierapp.entity.Restaurant;

public interface RestaurantService {
    Restaurant create(RestaurantDto restaurantDto);

    Restaurant getRestaurantById(String id);
}
