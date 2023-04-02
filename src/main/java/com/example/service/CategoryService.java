package com.example.service;

import com.example.entity.Category;

public interface CategoryService {
    Category add(Category category);
    Category update(Category category);
    boolean deleteById(Long id);

    Category findById(Long id);
}







