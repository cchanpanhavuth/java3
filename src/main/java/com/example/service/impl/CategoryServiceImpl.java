package com.example.service.impl;

import com.example.configuration.exceptions.NotFoundException;
import com.example.configuration.exceptions.TranscationException;
import com.example.entity.CarModel;
import com.example.entity.Category;
import com.example.entity.enums.StatusEnum;
import com.example.entity.projection.CarModelProjection;
import com.example.entity.projection.CategoryProjection;
import com.example.entity.response.ApiStatus;
import com.example.entity.response.Pagination;
import com.example.repository.CategoryRepository;
import com.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

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
        Category insertedCategory = null;
        try{
            insertedCategory = categoryRepository.save(category);
        }catch (Exception e){
            e.printStackTrace();
            throw new TranscationException(
                    ApiStatus.FAI_CREATED.getCode(),
                    ApiStatus.FAI_CREATED.getMessage());
        }
        return insertedCategory;
    }
    @Transactional
    @Override
    public Category update(Category category) {
        Category categoryToUpdate = categoryRepository.findById(category.getId())
                .orElseThrow(() -> new NotFoundException(
                        ApiStatus.NOT_FOUND.getCode(), ApiStatus.NOT_FOUND.getMessage()));
        try{
            if(!ObjectUtils.isEmpty(category)){
                categoryToUpdate.setCategoryName(category.getCategoryName());
                return categoryRepository.save(categoryToUpdate);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new TranscationException(ApiStatus.FAI_UPDATED.getCode(), ApiStatus.FAI_UPDATED.getMessage());
        }
        return null;
    }
    @Override
    public boolean deleteById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException(
                ApiStatus.NOT_FOUND.getCode(), ApiStatus.NOT_FOUND.getMessage()));
        if(!ObjectUtils.isEmpty(category)){
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Category findById(Long id) {

        return this.categoryRepository.findById(id).orElseThrow(() -> new NotFoundException(
                ApiStatus.NOT_FOUND.getCode(), ApiStatus.NOT_FOUND.getMessage()));
    }

    @Override
    public CategoryProjection findByCategoryName(String categoryName) {
        return this.categoryRepository.findByCategoryName(categoryName).orElseThrow(
                () -> new NotFoundException(
                        ApiStatus.NOT_FOUND.getCode(),
                        ApiStatus.NOT_FOUND.getMessage()
                )
        );
    }

    @Override
    public List<CategoryProjection> findAllCategory() {
        return categoryRepository.findAllCategoryBy();
    }

    @Override
    public List<CategoryProjection> findCategoryProjectionAll(Pagination pagination) {
        Page<CategoryProjection> cate = categoryRepository.findAllCategoryProjectionBy(
                PageRequest.of(pagination.getPage()-1, pagination.getSize())
        );
        pagination.setTotalCounts(cate.getTotalElements());
        return cate.getContent() ;
    }
    @Override
    public CategoryProjection findCategoryProjectionById(Long id) {
        return this.categoryRepository.findCategoryProjectionById(id).orElseThrow(
                () -> new NotFoundException(
                        ApiStatus.NOT_FOUND.getCode(),
                        ApiStatus.NOT_FOUND.getMessage()
                )
        );
    }
    @Override
    public long countCategoryByStatus(StatusEnum statusEnum) {

        return this.categoryRepository.countCategoryByStatus(statusEnum);
    }

    @Override
    public List<CategoryProjection> findCategoryProjectionByCategoryNameContainingIgnoreCase(String model, Pagination pagination) {
        Page<CategoryProjection> categoryProjections = this.categoryRepository.findCategoryProjectionByCategoryNameContainingIgnoreCase(
                model, PageRequest.of(pagination.getPage()-1, pagination.getSize())
        );
        pagination.setTotalCounts(categoryProjections.getTotalElements());
        return categoryProjections.getContent() ;
    }
}
