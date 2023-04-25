package com.example.repository;

import com.example.entity.OrderCars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderCarsRepository extends JpaRepository<OrderCars, Long> {
}
