package com.example.service;

import com.example.entity.CarModel;
import com.example.entity.enums.StatusEnum;
import com.example.entity.projection.CarFeaturesProjection;
import com.example.entity.projection.CarModelProjection;
import com.example.entity.response.Pagination;

import java.util.List;


public interface CarModelService {
    CarModel add(CarModel carModel);
    CarModel update(CarModel carModel);
    boolean deleteById(Long id);
    CarModel findById(Long id);
//    CarModelProjection findCarModelByName(String name);

    List<CarModelProjection> findAllCarModel();

    List<CarModelProjection> findCarModelProjectionAll(Pagination pagination);
    CarModelProjection findModelProjectionById(Long id);

    long countModelByStatus(StatusEnum statusEnum);

    List<CarModelProjection> findCarModelProjectionByModelContainingIgnoreCase(String model, Pagination pagination);

}
