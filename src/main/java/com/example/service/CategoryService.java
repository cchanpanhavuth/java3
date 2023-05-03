package com.example.service;

import com.example.entity.Category;
import com.example.entity.enums.StatusEnum;
import com.example.entity.projection.CarModelProjection;
import com.example.entity.projection.CategoryProjection;
import com.example.entity.response.Pagination;

import java.util.List;

public interface CategoryService {
    Category add(Category category);
    Category update(Category category);
    boolean deleteById(Long id);

    Category findById(Long id);


    CategoryProjection findByCategoryName(String categoryName);

    List<CategoryProjection> findAllCategory();

    List<CategoryProjection> findCategoryProjectionAll(Pagination pagination);
    CategoryProjection findCategoryProjectionById(Long id);

    long countCategoryByStatus(StatusEnum statusEnum);

    List<CategoryProjection> findCategoryProjectionByCategoryNameContainingIgnoreCase(String categoryName, Pagination pagination);
}







