package com.example.controller;

import com.example.entity.Car;
import com.example.entity.CarFeatures;
import com.example.entity.CarModel;
import com.example.entity.enums.StatusEnum;
import com.example.entity.projection.CarFeaturesProjection;
import com.example.entity.projection.CarModelProjection;
import com.example.entity.request.*;
import com.example.entity.response.ApiResponse;
import com.example.entity.response.ApiStatus;
import com.example.entity.response.Pagination;
import com.example.service.CarModelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.configuration.exceptions.TranscationException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/carModel")
public class CarModelRestController {

    private CarModelService carModelService;
    @Autowired
    public CarModelRestController(CarModelService CarModelService){
        this.carModelService = CarModelService;
    }


    @PostMapping
    public ApiResponse add(@RequestBody CarModelReq req){
        CarModel model = new CarModel();
        BeanUtils.copyProperties(req, model);

        CarModel insertedModel = carModelService.add(model);
        CarModelProjection modelProjection = this.carModelService.findModelProjectionById(insertedModel.getId());
        return new ApiResponse<>(
                ApiStatus.SUC_CREATED.getCode(),
                ApiStatus.SUC_CREATED.getMessage()
                /*,modelProjection*/ );
    }

    @PutMapping("/{id}")
    public ApiResponse update(@PathVariable Long id , @RequestBody CarModelReq req){
        // TODO: Prepare User Object
        CarModel model = new CarModel();
        CarModelProjection modelProjection = null;
        try{
            BeanUtils.copyProperties(req, model);
            model.setId(id);
            CarModel updatedModel = carModelService.update(model);
            modelProjection = this.carModelService.findModelProjectionById(updatedModel.getId());
        }catch (Exception e){
            e.printStackTrace();
            throw new TranscationException(ApiStatus.FAI_UPDATED.getCode(), ApiStatus.FAI_UPDATED.getMessage());
        }
        return new ApiResponse<>(
                ApiStatus.SUC_UPDATED.getCode(),
                ApiStatus.SUC_UPDATED.getMessage(), modelProjection);
    }
    @DeleteMapping("/{id}")
    public ApiResponse deleteUserById(@PathVariable Long id){
        this.carModelService.deleteById(id);
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
    public ApiResponse<List<CarModelProjection>> findCarModelByModelContaining(
            @RequestBody CarModelFilter carModelFilter,
            @Parameter(hidden = true) Pagination pagination){
        List<CarModelProjection> user = this.carModelService.findCarModelProjectionByModelContainingIgnoreCase(carModelFilter.getModel(), pagination);
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
    public ApiResponse<Long> countModelByStatus(){
        return new ApiResponse<>(
                ApiStatus.SUC_RETRIEVED.getCode(),
                ApiStatus.SUC_RETRIEVED.getMessage(),
                this.carModelService.countModelByStatus( StatusEnum.ACT)
        );
    }

    @GetMapping("/{id}")
    public ApiResponse findModelById(@PathVariable Long id){
        CarModelProjection feat = this.carModelService.findModelProjectionById(id);
        if(feat != null){
            return new ApiResponse<>(
                    ApiStatus.SUC_RETRIEVED.getCode(),
                    ApiStatus.SUC_RETRIEVED.getMessage(),
                    feat
            );
        }
        return new ApiResponse<>(ApiStatus.NOT_FOUND.getCode(), ApiStatus.NOT_FOUND.getMessage());
    }
}
