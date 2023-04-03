package com.example.controller;

import com.example.entity.CarFeatures;
import com.example.service.CarFeaturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carFeatures")
public class CarFeaturesRestController {
    private CarFeaturesService carFeaturesService;
    @Autowired
    public CarFeaturesRestController(CarFeaturesService carFeaturesService){
        this.carFeaturesService = carFeaturesService;
    }

    @PostMapping
    public CarFeatures add(@RequestBody CarFeatures carFeatures){
        return this.carFeaturesService.add(carFeatures);
    }

    @PutMapping
    public CarFeatures update(@RequestBody CarFeatures carFeatures){
        return this.carFeaturesService.update(carFeatures);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id){
        return this.carFeaturesService.deleteById(id);
    }
}
