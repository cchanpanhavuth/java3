package com.example.repository;

import com.example.entity.Category;
import com.example.entity.enums.StatusEnum;
import com.example.entity.projection.CarModelProjection;
import com.example.entity.projection.CategoryProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<CategoryProjection> findByCategoryName(String categoryName);
    List<CategoryProjection> findAllCategoryBy();

    Page<CategoryProjection> findAllCategoryProjectionBy(Pageable pageable);
    Page<CategoryProjection> findCategoryProjectionByCategoryNameContainingIgnoreCase(String categoryName, Pageable pageable);

    Optional<CategoryProjection> findCategoryProjectionById(Long id);
    @Query(value = "SELECT count(id) FROM Category where status= :status" , nativeQuery = false)
    long countCategoryByStatus( @Param("status") StatusEnum statusEnum);


}
