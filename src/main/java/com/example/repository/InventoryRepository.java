package com.example.repository;

import com.example.entity.Inventory;
import com.example.entity.projection.CarProjection;
import com.example.entity.projection.InventoryProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<InventoryProjection> findInventorById(Long id);
    List<InventoryProjection> findAllInventoryBy();
    Page<InventoryProjection> findAllInventoryProjectionBy(Pageable pageable);
}
