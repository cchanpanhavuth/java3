package com.example.repository;

import com.example.entity.Car;
import com.example.entity.projection.CarProjection;
import com.example.entity.projection.CategoryProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<CarProjection> findByCarPrice(Double price);
    List<CarProjection> findAllCarBy();

    Page<CarProjection> findAllCarProjectionBy(Pageable pageable);



}
