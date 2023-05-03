package com.example.controller;

import com.example.configuration.exceptions.TranscationException;
import com.example.entity.CarModel;
import com.example.entity.Category;
import com.example.entity.enums.StatusEnum;
import com.example.entity.projection.CarModelProjection;
import com.example.entity.projection.CategoryProjection;
import com.example.entity.request.CarModelFilter;
import com.example.entity.request.CarModelReq;
import com.example.entity.request.CategoryFilter;
import com.example.entity.request.CategoryReq;
import com.example.entity.response.ApiResponse;
import com.example.entity.response.ApiStatus;
import com.example.entity.response.Pagination;
import com.example.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.BeanUtils;
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
    public ApiResponse add(@RequestBody CategoryReq req){
        Category cate = new Category();
        BeanUtils.copyProperties(req, cate);
        Category insertedCategory = categoryService.add(cate);
        CategoryProjection cateProjection = this.categoryService.findCategoryProjectionById(insertedCategory.getId());
        return new ApiResponse<>(
                ApiStatus.SUC_CREATED.getCode(),
                ApiStatus.SUC_CREATED.getMessage()
                /*,modelProjection*/ );
    }
    @PutMapping("/{id}")
    public ApiResponse update(@PathVariable Long id , @RequestBody CategoryReq req){
        Category cate = new Category();
        CategoryProjection cateProjection = null;
        try{
            BeanUtils.copyProperties(req, cate);
            cate.setId(id);
            Category updatedCategory = categoryService.update(cate);
            cateProjection = this.categoryService.findCategoryProjectionById(updatedCategory.getId());
        }catch (Exception e){
            e.printStackTrace();
            throw new TranscationException(ApiStatus.FAI_UPDATED.getCode(), ApiStatus.FAI_UPDATED.getMessage());
        }
        return new ApiResponse<>(
                ApiStatus.SUC_UPDATED.getCode(),
                ApiStatus.SUC_UPDATED.getMessage(),
                cateProjection);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteUserById(@PathVariable Long id){
        this.categoryService.deleteById(id);
        return new ApiResponse(ApiStatus.SUC_DELETED.getCode(), ApiStatus.SUC_DELETED.getMessage());
    }

    @Operation(summary = "Find Data")
    @Parameters({
            @Parameter(in = ParameterIn.QUERY
                    , description = "Page you want to retrieve (1..N)"
                    , name = "page"
                    , content = @Content(schema = @Schema(type = "integer", defaultValue = "1"))),
            @Parameter(in = ParameterIn.QUERY
                    , description = "Number of records per page."
                    , name = "size"
                    , content = @Content(schema = @Schema(type = "integer", defaultValue = "20")))
    })
    @PostMapping("/filter")
    public ApiResponse<List<CategoryProjection>> findCategoryByCategoryNameContaining(
            @RequestBody CategoryFilter categoryFilter,
            @Parameter(hidden = true) Pagination pagination){
        List<CategoryProjection> user = this.categoryService.findCategoryProjectionByCategoryNameContainingIgnoreCase(categoryFilter.getCategoryName(), pagination);
        if(user != null){
            return new ApiResponse<>(
                    ApiStatus.SUC_RETRIEVED.getCode(),
                    ApiStatus.SUC_RETRIEVED.getMessage(),
                    user,
                    pagination
            );
        }
        return new ApiResponse<>(ApiStatus.NOT_FOUND.getCode(), ApiStatus.NOT_FOUND.getMessage());
    }

    @GetMapping("/count")
    public ApiResponse<Long> countCategoryByStatus(){
        return new ApiResponse<>(
                ApiStatus.SUC_RETRIEVED.getCode(),
                ApiStatus.SUC_RETRIEVED.getMessage(),
                this.categoryService.countCategoryByStatus( StatusEnum.ACT)
        );
    }

    @GetMapping("/{id}")
    public ApiResponse findCategoryById(@PathVariable Long id){
        CategoryProjection cate = this.categoryService.findCategoryProjectionById(id);
        if(cate != null){
            return new ApiResponse<>(
                    ApiStatus.SUC_RETRIEVED.getCode(),
                    ApiStatus.SUC_RETRIEVED.getMessage(),
                    cate
            );
        }
        return new ApiResponse<>(ApiStatus.NOT_FOUND.getCode(), ApiStatus.NOT_FOUND.getMessage());
    }
}
