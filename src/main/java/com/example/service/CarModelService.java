package com.example.service;

import com.example.entity.CarModel;



public interface CarModelService {
    CarModel add(CarModel carModel);
    CarModel update(CarModel carModel);
    boolean deleteById(Long id);
    CarModel findById(Long id);
}
