package com.bogdan.courierapp.repository;

import com.bogdan.courierapp.dto.SupportManagerDto;
import com.bogdan.courierapp.entity.Courier;
import com.bogdan.courierapp.entity.SupportManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Repository
public interface CourierRepository extends JpaRepository<Courier, UUID> {

    @Query("SELECT c FROM Courier c WHERE c.id = :id")
    Courier findCourierById(UUID id);

    @Modifying
    @Query("update Courier c set c.courierName = :name where c.id = :uuid")
    void updateCourierName(UUID uuid, String name);

    @Modifying
    @Query("update Courier c set c.supportManager.id = :managerId where c.id = :uuid")
    void updateCourierManager(UUID uuid, UUID managerId);


    @Query("SELECT c FROM Courier c WHERE c.courierName = :username")
    Courier findByUsername(String username);
}
