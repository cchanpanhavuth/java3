package com.example.service;

import com.example.entity.Car;
import com.example.entity.enums.StatusEnum;
import com.example.entity.projection.CarFeaturesProjection;
import com.example.entity.projection.CarProjection;
import com.example.entity.projection.CategoryProjection;
import com.example.entity.response.Pagination;

import java.util.List;

public interface CarService {
    Car add(Car car);
    Car update(Car car);
    boolean deleteById(Long id);
    Car findById(Long id);

    CarProjection findByPrice(Double price);

    //List<CarProjection> findAllCar();

    List<CarProjection> findCarProjectionAll(Pagination pagination);
    long countCarByStatus(StatusEnum statusEnum);

    List<CarProjection> findCarProjectionByYearContainingIgnoreCase(String year, Pagination pagination);

    CarProjection findCarProjectionById(Long id);
}
