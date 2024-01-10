package com.bogdan.courierapp.repository;

import com.bogdan.courierapp.entity.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Repository
public interface TestRepository extends JpaRepository<Courier, UUID> {

    @Query("SELECT c FROM Courier c WHERE c.id = :id")
    Courier findCourierById(@PathVariable("id") UUID id);


}
