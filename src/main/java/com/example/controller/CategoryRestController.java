package com.example.controller;

import com.example.entity.Category;
import com.example.entity.projection.CategoryProjection;
import com.example.entity.response.Pagination;
import com.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryRestController {
    private CategoryService categoryService;
    @Autowired
    public CategoryRestController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("/categoryName/{categoryName}")
    public CategoryProjection findByCategoryName(@PathVariable String categoryName){
        return this.categoryService.findByCategoryName(categoryName);
    }

    @GetMapping("/all")
    public Map<String, Object> findByCategoryName(Pagination pagination){
        List<CategoryProjection> cate = this.categoryService.findCategoryProjectionAll(pagination);
        Map<String, Object> map = new HashMap<>();
        map.put("data", cate);
        map.put("pagination", pagination);
        return map;
    }

    @GetMapping
    public List<CategoryProjection> findByCategoryName(){
        return this.categoryService.findAllCategory();
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
