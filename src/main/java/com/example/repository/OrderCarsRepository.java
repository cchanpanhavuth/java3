package com.example.repository;

import com.example.entity.OrderCars;
import com.example.entity.projection.CarProjection;
import com.example.entity.projection.InventoryProjection;
import com.example.entity.projection.OrderCarProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderCarsRepository extends JpaRepository<OrderCars, Long> {
    Optional<OrderCarProjection> findByOrderCarId(Long id);
    Optional<OrderCarProjection> findByCustomerId(Long id);
    Optional<OrderCarProjection> findByStaffId(Long id);

    List<OrderCarProjection> findAllOrderCarBy();

    Page<OrderCarProjection> findAllOrderCarProjectionBy(Pageable pageable);
}
