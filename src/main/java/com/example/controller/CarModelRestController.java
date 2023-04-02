package com.example.controller;

import com.example.entity.CarModel;
import com.example.service.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carModel")
public class CarModelRestController {

    private CarModelService CarmodelService;
    @Autowired
    public CarModelRestController(CarModelService CarModelService){
        this.CarmodelService = CarModelService;
    }

    @PostMapping
    public CarModel add(@RequestBody CarModel carModel){
        return this.CarmodelService.add(carModel);
    }

    @PutMapping
    public CarModel update(@RequestBody CarModel carModel){
        return this.CarmodelService.update(carModel);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id){
        return this.CarmodelService.deleteById(id);
    }
}
