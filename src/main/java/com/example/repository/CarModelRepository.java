package com.example.repository;

import com.example.entity.CarModel;
import com.example.entity.enums.StatusEnum;
import com.example.entity.projection.CarFeaturesProjection;
import com.example.entity.projection.CarModelProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarModelRepository extends JpaRepository<CarModel, Long> {
    Optional<CarModelProjection> findCarModelProjectionById(Long id);
//    Optional<CarModelProjection> findCarModelByName(String name);
    List<CarModelProjection> findAllCarModelBy();
    Page<CarModelProjection> findAllCarModelProjectionBy(Pageable pageable);
    Page<CarModelProjection> findCarModelProjectionByModelContainingIgnoreCase(String model, Pageable pageable);


    @Query(value = "SELECT count(id) FROM CarModel where status= :status" , nativeQuery = false)
    long countCarModelByStatus( @Param("status") StatusEnum statusEnum);

}
