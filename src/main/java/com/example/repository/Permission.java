package com.example.repository;

import com.example.entity.projection.PermissionProjection;
import com.example.entity.projection.RoleProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Permission extends JpaRepository {
    Optional<PermissionProjection> findPermissionProjectionById(Long id);

    Page<PermissionProjection> findPermissionProjectionByIdContainingIgnoreCase(String id, Pageable pageable);

}