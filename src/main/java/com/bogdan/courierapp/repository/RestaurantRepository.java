package com.bogdan.courierapp.repository;

import com.bogdan.courierapp.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {
    Restaurant getRestaurantByRestaurantName(String restaurantName);
}
