package com.bogdan.courierapp.service.inter;

import com.bogdan.courierapp.dto.RestaurantDto;
import com.bogdan.courierapp.entity.Restaurant;

import java.util.List;

public interface RestaurantService {
    Restaurant create(RestaurantDto restaurantDto);

    Restaurant getRestaurantById(String id);

    List<Restaurant> getRestaurantsByDeliveryZoneId(String deliveryZoneId);

    List<Restaurant> getRestaurantsByOwner(String owner);

    void deleteById(String restaurantId);
}
