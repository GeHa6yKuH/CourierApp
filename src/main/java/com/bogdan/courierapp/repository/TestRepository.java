package com.bogdan.courierapp.repository;

import com.bogdan.courierapp.entity.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TestRepository extends JpaRepository<Courier, UUID> {
    @Query(value = "SELECT * FROM courier WHERE courier_id = :id", nativeQuery = true)
    Courier getCourierById(@Param("id") String courierId);

}
