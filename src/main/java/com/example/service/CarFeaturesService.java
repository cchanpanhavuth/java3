package com.example.service;

import com.example.entity.CarFeatures;

public interface CarFeaturesService {
    CarFeatures add(CarFeatures carModel);
    CarFeatures update(CarFeatures carModel);
    boolean deleteById(Long id);
    CarFeatures findById(Long id);
}
