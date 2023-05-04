package com.example.controller;

import com.example.configuration.exceptions.TranscationException;
import com.example.entity.*;
import com.example.entity.enums.GenderEnum;
import com.example.entity.enums.StatusEnum;
import com.example.entity.projection.CarFeaturesProjection;
import com.example.entity.projection.CarProjection;
import com.example.entity.request.*;
import com.example.entity.response.ApiResponse;
import com.example.entity.response.ApiStatus;
import com.example.entity.response.Pagination;
import com.example.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/car")
public class CarController {
    private CarService carService;
    private CategoryService categoryService;
    private CarModelService carModelService;
    private InventoryService inventoryService;
    private BrandService brandService;
    private ManufacturerLocationService manufacturerLocationService;
    private BranchService branchService;
    private CarFeaturesService carFeaturesService;

    @Autowired
    public CarController(
            CarService carService,
            CategoryService categoryService,
            CarModelService carModelService,
            InventoryService inventoryService,
            BrandService brandService,
            ManufacturerLocationService manufacturerLocationService,
            BranchService branchService,
            CarFeaturesService carFeaturesService
    ){
        this.carService = carService;
        this.categoryService = categoryService;
        this.carModelService = carModelService;
        this.inventoryService = inventoryService;
        this.branchService = branchService;
        this.manufacturerLocationService = manufacturerLocationService;
        this.brandService = brandService;
        this.carFeaturesService = carFeaturesService;
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteCarById(@PathVariable Long id){
        this.carService.deleteById(id);
        return new ApiResponse(ApiStatus.SUC_DELETED.getCode(), ApiStatus.SUC_DELETED.getMessage());
    }

    @GetMapping("/price/{price}")
    public ApiResponse findCarByPrice(@PathVariable Double price){
        CarProjection car = this.carService.findByPrice(price);
        if(car != null){
            return new ApiResponse<>(
                    ApiStatus.SUC_RETRIEVED.getCode(),
                    ApiStatus.SUC_RETRIEVED.getMessage(),
                    car
            );
        }
        return new ApiResponse<>(ApiStatus.NOT_FOUND.getCode(), ApiStatus.NOT_FOUND.getMessage());
    }

    @GetMapping("/{id}")
    public ApiResponse findCarById(@PathVariable Long id){
        CarProjection car = this.carService.findCarProjectionById(id);
        if(car != null){
            return new ApiResponse<>(
                    ApiStatus.SUC_RETRIEVED.getCode(),
                    ApiStatus.SUC_RETRIEVED.getMessage(),
                    car
            );
        }
        return new ApiResponse<>(ApiStatus.NOT_FOUND.getCode(), ApiStatus.NOT_FOUND.getMessage());
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
    public ApiResponse<List<CarProjection>> findCarByYearContaining(
            @RequestBody CarFilter carFilter,
            @Parameter(hidden = true) Pagination pagination){
        List<CarProjection> car = this.carService.findCarProjectionByYearContainingIgnoreCase(carFilter.getYear(), pagination);
        if(car != null){
            return new ApiResponse<>(
                    ApiStatus.SUC_RETRIEVED.getCode(),
                    ApiStatus.SUC_RETRIEVED.getMessage(),
                    car,
                    pagination
            );
        }
        return new ApiResponse<>(ApiStatus.NOT_FOUND.getCode(), ApiStatus.NOT_FOUND.getMessage());
    }

    @GetMapping("/count")
    public ApiResponse<Long> countFeatureByStatus(){
        return new ApiResponse<>(
                ApiStatus.SUC_RETRIEVED.getCode(),
                ApiStatus.SUC_RETRIEVED.getMessage(),
                this.carService.countCarByStatus( StatusEnum.ACT)
        );
    }

    @PostMapping
    public ApiResponse add(@RequestBody CarReq req){
        Category category = this.categoryService.findById(req.getCategoryId());
        CarModel model = this.carModelService.findById(req.getModelId());
        CarFeatures feature = this.carFeaturesService.findById(req.getFeatureId());
        Inventory inventory = this.inventoryService.findById(req.getInventoryId());
        Brand brand = this.brandService.findById(req.getBrandId());
        Branch branch = this.branchService.findById(req.getBranchId());
        ManufacturerLocation local = this.manufacturerLocationService.findById(req.getManufactureId());
        if(category == null || model == null || feature==null
                || inventory == null || brand == null || branch == null || local ==null
        ){
            System.out.println("Field is not found!");
            return null;

        }
        Car car = new Car();
        BeanUtils.copyProperties(req, car);
        car.setCategoryId(category);
        car.setModelId(model);
        car.setFeaturesId(feature);
        car.setInventoryId(inventory);
        car.setBrandId(brand);
        car.setBranchId(branch);
        car.setManufactureId(local);
        Car insertedcar = carService.add(car);
        CarProjection carProjection = this.carService.findCarProjectionById(insertedcar.getId());
        return new ApiResponse<>(
                ApiStatus.SUC_CREATED.getCode(),
                ApiStatus.SUC_CREATED.getMessage()
                /*,carProjection*/ );
    }

    @PutMapping("/{id}")
    public ApiResponse update(@PathVariable Long id , @RequestBody CarReq req){
        // TODO: Prepare User Object
        Car car = new Car();
        CarProjection carProjection = null;
        try{
            BeanUtils.copyProperties(req, car);
            car.setId(id);
            Car updatedCar = carService.update(car);
            carProjection = this.carService.findCarProjectionById(updatedCar.getId());
        }catch (Exception e){
            e.printStackTrace();
            throw new TranscationException(ApiStatus.FAI_UPDATED.getCode(), ApiStatus.FAI_UPDATED.getMessage());
        }
        return new ApiResponse<>(
                ApiStatus.SUC_UPDATED.getCode(),
                ApiStatus.SUC_UPDATED.getMessage(),
                carProjection);
    }
}
