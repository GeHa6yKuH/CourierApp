package com.bogdan.courierapp.repository;

import com.bogdan.courierapp.entity.DeliveryZone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DeliveryZoneRepository extends JpaRepository<DeliveryZone, UUID> {

}
