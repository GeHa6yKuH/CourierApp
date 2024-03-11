package com.bogdan.courierapp.repository;

import com.bogdan.courierapp.entity.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AppRoleRepository extends JpaRepository<AppRole, UUID> {

}
