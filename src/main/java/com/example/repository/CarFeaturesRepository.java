package com.example.repository;

import com.example.entity.CarFeatures;
import com.example.entity.enums.StatusEnum;
import com.example.entity.projection.CarFeaturesProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarFeaturesRepository extends JpaRepository<CarFeatures, Long> {
    Optional<CarFeaturesProjection> findCarFeatureProjectionById(Long id);
    Optional<CarFeaturesProjection> findCarFeaturesByFeatureDescription(String name);
    List<CarFeaturesProjection> findAllCarFeaturesBy();

    Page<CarFeaturesProjection> findAllCarFeaturesProjectionBy(Pageable pageable);
    Page<CarFeaturesProjection> findCarFeaturesProjectionByFeatureDescriptionContainingIgnoreCase(String featureDescription, Pageable pageable);


    @Query(value = "SELECT count(id) FROM CarFeatures where status= :status" , nativeQuery = false)
    long countCarFeaturesByStatus( @Param("status") StatusEnum statusEnum);

    @Query(value = "SELECT count(id) FROM User where status= :status")
    Long countUserByStatus( @Param("status") StatusEnum statusEnum);
}