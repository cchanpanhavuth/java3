package com.example.service;

import com.example.entity.CarFeatures;
import com.example.entity.projection.CarFeaturesProjection;
import com.example.entity.projection.CategoryProjection;
import com.example.entity.response.Pagination;

import java.util.List;

public interface CarFeaturesService {
    CarFeatures add(CarFeatures carModel);
    CarFeatures update(CarFeatures carModel);
    boolean deleteById(Long id);
    CarFeatures findById(Long id);


    CarFeaturesProjection findCarFeaturesByFeatureDescription(String featureDescription);

    List<CarFeaturesProjection> findAllCarFeatures();

    List<CarFeaturesProjection> findCarFeaturesProjectionAll(Pagination pagination);
}
