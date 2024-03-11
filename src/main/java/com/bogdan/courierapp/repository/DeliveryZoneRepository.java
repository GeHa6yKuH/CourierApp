package com.bogdan.courierapp.repository;

import com.bogdan.courierapp.entity.Courier;
import com.bogdan.courierapp.entity.DeliveryZone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Repository
public interface DeliveryZoneRepository extends JpaRepository<DeliveryZone, UUID> {

}
