package com.example.service;

import com.example.entity.Car;

public interface CarService {
    Car add(Car car);
    Car update(Car car);
    boolean deleteById(Long id);
    Car findById(Long id);
}
