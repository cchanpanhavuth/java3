package com.example.repository;

import com.example.entity.projection.RoleProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository {
    Optional<RoleProjection> findRoleProjectionById(Long id);

    Page<RoleProjection> findRoleProjectionByIdContainingIgnoreCase(String id, Pageable pageable);

}
