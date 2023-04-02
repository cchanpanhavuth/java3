package com.example.controller;

import com.example.entity.Category;
import com.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryRestController {
    private CategoryService categoryService;
    @Autowired
    public CategoryRestController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping
    public Category add(@RequestBody Category category){
        return this.categoryService.add(category);
    }

    @PutMapping
    public Category update(@RequestBody Category category){
        return this.categoryService.update(category);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id){
        return this.categoryService.deleteById(id);
    }
}
