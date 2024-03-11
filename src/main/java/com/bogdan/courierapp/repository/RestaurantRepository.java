package com.bogdan.courierapp.repository;

import com.bogdan.courierapp.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {
    Restaurant getRestaurantByRestaurantName(String restaurantName);

    @Query("SELECT r FROM Restaurant r WHERE r.deliveryZone.id = :deliveryZoneId")
    List<Restaurant> getRestaurantsByDeliveryZoneId(String deliveryZoneId);

    @Query("SELECT r FROM Restaurant r WHERE r.owner = :owner")
    List<Restaurant> getRestaurantsByOwner(String owner);

}
