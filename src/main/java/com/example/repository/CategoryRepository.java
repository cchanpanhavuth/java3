package com.example.repository;

import com.example.entity.Category;
import com.example.entity.projection.CategoryProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<CategoryProjection> findByCategoryName(String categoryName);
    List<CategoryProjection> findAllCategoryBy();

    Page<CategoryProjection> findAllCategoryProjectionBy(Pageable pageable);

}
