package com.bogdan.courierapp.repository;

import com.bogdan.courierapp.entity.SupportManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SupportManagerRepository extends JpaRepository<SupportManager, UUID> {
    @Query("select m from SupportManager m where m.name like concat('%', :firstOrLastName, '%')")
    List<SupportManager> findByPatternName(String firstOrLastName);

    @Query("select m from SupportManager m where m.name = :name")
    List<SupportManager> findByName(String name);

}
