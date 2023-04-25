package com.example.repository;

import com.example.entity.CarFeatures;
import com.example.entity.projection.CarFeaturesProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarFeaturesRepository extends JpaRepository<CarFeatures, Long> {

    Optional<CarFeaturesProjection> findCarFeaturesByFeatureDescription(String name);
    List<CarFeaturesProjection> findAllCarFeaturesBy();

    Page<CarFeaturesProjection> findAllCarFeaturesProjectionBy(Pageable pageable);
}