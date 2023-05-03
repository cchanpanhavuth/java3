package com.example.repository;

import com.example.entity.projection.SaleStaffProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;



public interface SaleStaff extends JpaRepository {
    Optional<SaleStaffProjection> findPermissionProjectionById(Long id);

    Page<SaleStaffProjection> findPermissionProjectionByIdContainingIgnoreCase(String id, Pageable pageable);

}
