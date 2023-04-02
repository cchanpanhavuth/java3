package com.example.service.impl;

import com.example.entity.Car;
import com.example.repository.CarRepository;
import com.example.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {
    private CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    @Override
    public Car add(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car update(Car car) {
        Car carToUpdate = this.carRepository.findById(car.getId()).orElse(null);
        if(carToUpdate == null){
            System.out.println("Product is not found!");
            return null;
        }
        carToUpdate.setBrand_name(car.getBrand_name());
        carToUpdate.setPrice(car.getPrice());
        carToUpdate.setCategory_name(car.getCategory_name());
        carToUpdate.setModel_name(car.getModel_name());
        carToUpdate.setBranch_name(car.getBranch_name());
        carToUpdate.setInventory(car.getInventory());
        carToUpdate.setFeatures_id(car.getFeatures_id());
        carToUpdate.setManufacturer_location(car.getManufacturer_location());
        return this.carRepository.save(carToUpdate);
    }

    @Override
    public boolean deleteById(Long id) {
        Car car = this.carRepository.findById(id).orElse(null);
        if(car == null){
            return false;
        }
        carRepository.deleteById(id);
        return true;
    }

    @Override
    public Car findById(Long id) {
        return carRepository.findById(id).orElse(null);
    }
}
