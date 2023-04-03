package com.example.repository;

import com.example.entity.CarFeatures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarFeaturesRepository extends JpaRepository<CarFeatures, Long> {
}