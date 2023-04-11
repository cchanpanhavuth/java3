package com.example.service;

import com.example.entity.CarModel;
import com.example.entity.projection.CarModelProjection;
import com.example.entity.response.Pagination;

import java.util.List;


public interface CarModelService {
    CarModel add(CarModel carModel);
    CarModel update(CarModel carModel);
    boolean deleteById(Long id);
    CarModel findById(Long id);
    CarModelProjection findByName(String name);

    List<CarModelProjection> findAll();

    List<CarModelProjection> findCarModelProjectionAll(Pagination pagination);
}
