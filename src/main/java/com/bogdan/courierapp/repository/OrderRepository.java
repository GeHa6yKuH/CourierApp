package com.bogdan.courierapp.repository;

import com.bogdan.courierapp.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    @Query("SELECT o FROM Order o WHERE o.restaurantId= :restaurantId")
    List<Order> findByRestaurantId(UUID restaurantId);
}
