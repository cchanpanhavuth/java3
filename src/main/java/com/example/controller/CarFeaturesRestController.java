package com.example.controller;

import com.example.configuration.exceptions.TranscationException;
import com.example.entity.CarFeatures;
import com.example.entity.enums.StatusEnum;
import com.example.entity.projection.CarFeaturesProjection;
import com.example.entity.request.CarFeaturesFilter;
import com.example.entity.request.CarFeaturesReq;
import com.example.entity.response.ApiResponse;
import com.example.entity.response.ApiStatus;
import com.example.entity.response.Pagination;
import com.example.service.CarFeaturesService;
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
@RequestMapping("/carFeatures")
public class CarFeaturesRestController {
    private CarFeaturesService carFeaturesService;
    @Autowired
    public CarFeaturesRestController(CarFeaturesService carFeaturesService){
        this.carFeaturesService = carFeaturesService;
    }

    @GetMapping("/featureDescription/{featureDescription}")
    public ApiResponse findByFeatureDescription(@PathVariable String featureDescription){
        CarFeaturesProjection feat = this.carFeaturesService.findCarFeaturesByFeatureDescription(featureDescription);
        if(feat != null){
            return new ApiResponse<>(
                    ApiStatus.SUC_RETRIEVED.getCode(),
                    ApiStatus.SUC_RETRIEVED.getMessage(),
                    feat
            );
        }
        return new ApiResponse<>(ApiStatus.NOT_FOUND.getCode(), ApiStatus.NOT_FOUND.getMessage());
    }

    @GetMapping("/all")
    public Map<String, Object> findAllFeatures(Pagination pagination){
        List<CarFeaturesProjection> cfeat = this.carFeaturesService.findCarFeaturesProjectionAll(pagination);
        Map<String, Object> map = new HashMap<>();
        map.put("data", cfeat);
        map.put("pagination", pagination);
        return map;
    }

//    @GetMapping
//    public List<CarFeaturesProjection> findByFeatureDescription(){
//        return this.carFeaturesService.findAllCarFeatures();
//    }
//
////    @PostMapping
////    public CarFeatures add(@RequestBody CarFeatures carFeatures){
////        return this.carFeaturesService.add(carFeatures);
////    }

    @PostMapping
    public ApiResponse add(@RequestBody CarFeaturesReq req){
        // TODO: Prepare User Object
        CarFeatures feature = new CarFeatures();
        BeanUtils.copyProperties(req, feature);

        // TODO: Prepare List of Address Object
//        List<Car> carsObjectList = new ArrayList<>();
//        if(!ObjectUtils.isEmpty(req.getCars())){
//            for(CarAddRequest carAddReq : req.getCars()){
//                Car carObject = new Car();
//                BeanUtils.copyProperties(carAddReq, carObject);
//                addressObject.setUser(user);
//                addressesObjectList.add(addressObject);
//            }
//            user.setAddresses(addressesObjectList);
//        }
        CarFeatures insertedFeature = carFeaturesService.add(feature);
        CarFeaturesProjection featuresProjection = this.carFeaturesService.findFeatureProjectionById(insertedFeature.getId());
        return new ApiResponse<>(
                ApiStatus.SUC_CREATED.getCode(),
                ApiStatus.SUC_CREATED.getMessage()
                /*,featuresProjection*/ );
    }

    @PutMapping("/{id}")
    public ApiResponse update(@PathVariable Long id , @RequestBody CarFeaturesReq req){
        // TODO: Prepare User Object
        CarFeatures feat = new CarFeatures();
        CarFeaturesProjection featuresProjection = null;
        try{
            BeanUtils.copyProperties(req, feat);
            feat.setId(id);
            // TODO: Prepare List of Address Object
//            List<Address> addressesObjectList = new ArrayList<>();
//            if(!ObjectUtils.isEmpty(req.getAddress())){
//                for(AddressReq addressAddReq : req.getAddress()){
//                    Address addressObject = new Address();
//                    BeanUtils.copyProperties(addressAddReq, addressObject);
//                    addressesObjectList.add(addressObject);
//                    addressObject.setUser(user);
//                }
//                user.setAddresses(addressesObjectList);
//            }

            CarFeatures updatedFeat = carFeaturesService.update(feat);
            featuresProjection = this.carFeaturesService.findFeatureProjectionById(updatedFeat.getId());
        }catch (Exception e){
            e.printStackTrace();
            throw new TranscationException(ApiStatus.FAI_UPDATED.getCode(), ApiStatus.FAI_UPDATED.getMessage());
        }
        return new ApiResponse<>(
                ApiStatus.SUC_UPDATED.getCode(),
                ApiStatus.SUC_UPDATED.getMessage(),
                featuresProjection);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteUserById(@PathVariable Long id){
        this.carFeaturesService.deleteById(id);
        return new ApiResponse(ApiStatus.SUC_DELETED.getCode(), ApiStatus.SUC_DELETED.getMessage());
    }

    @GetMapping("/{id}")
    public ApiResponse findFeatureById(@PathVariable Long id){
        CarFeaturesProjection feat = this.carFeaturesService.findFeatureProjectionById(id);
        if(feat != null){
            return new ApiResponse<>(
                    ApiStatus.SUC_RETRIEVED.getCode(),
                    ApiStatus.SUC_RETRIEVED.getMessage(),
                    feat
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
    public ApiResponse<List<CarFeaturesProjection>> findCarFeaturesByFeatureContaining(
            @RequestBody CarFeaturesFilter carFeaturesFilter,
            @Parameter(hidden = true) Pagination pagination){
        List<CarFeaturesProjection> user = this.carFeaturesService.findCarFeaturesProjectionByFeatureDescriptionContainingIgnoreCase(carFeaturesFilter.getFeatureDescription(), pagination);
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
    public ApiResponse<Long> countFeatureByStatus(){
        return new ApiResponse<>(
                ApiStatus.SUC_RETRIEVED.getCode(),
                ApiStatus.SUC_RETRIEVED.getMessage(),
                this.carFeaturesService.countFeatureByStatus( StatusEnum.ACT)
        );
    }
}
