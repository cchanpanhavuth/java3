package com.example.service.impl;

import com.example.entity.Category;
import com.example.entity.projection.CategoryProjection;
import com.example.entity.response.Pagination;
import com.example.repository.CategoryRepository;
import com.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;

    }

    @Override
    public Category add(Category category) {
        category.setCreatedBy("Admin");
        return this.categoryRepository.save(category);
    }
    @Override
    public Category update(Category category) {
        Category cate = this.categoryRepository.findById(category.getId()).orElse(null);
        if(cate == null){
            return null;
        }
        cate.setUpdatedBy("Dara");
        cate.setCategoryName(category.getCategoryName());
        return this.categoryRepository.save(cate);
    }
    @Override
    public boolean deleteById(Long id) {
        Category cate = this.categoryRepository.findById(id).orElse(null);
        if(cate == null){
            return false;
        }
        this.categoryRepository.deleteById(id);
        return true;
    }

    @Override
    public Category findById(Long id) {
        return this.categoryRepository.findById(id).orElse(null);
    }

    @Override
    public CategoryProjection findByCategoryName(String name) {
        return this.categoryRepository.findByCategoryName(name).orElse(null);
    }

    @Override
    public List<CategoryProjection> findAll() {
        return categoryRepository.findAllBy();
    }

    @Override
    public List<CategoryProjection> findCategoryProjectionAll(Pagination pagination) {
        Page<CategoryProjection> cate = categoryRepository.findAllCategoryProjectionBy(
                PageRequest.of(pagination.getPage()-1, pagination.getSize())
        );
        pagination.setTotalCounts(cate.getTotalElements());
        return cate.getContent() ;
    }
}
