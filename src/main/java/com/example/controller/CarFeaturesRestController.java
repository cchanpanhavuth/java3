package com.example.controller;

import com.example.entity.CarFeatures;
import com.example.entity.projection.CarFeaturesProjection;
import com.example.entity.response.Pagination;
import com.example.service.CarFeaturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/carFeatures")
public class CarFeaturesRestController {
    private CarFeaturesService carFeaturesService;
    @Autowired
    public CarFeaturesRestController(CarFeaturesService carFeaturesService){
        this.carFeaturesService = carFeaturesService;
    }

    @GetMapping("/featureDescription/{featureDescription}")
    public CarFeaturesProjection findByFeatureDescription(@PathVariable String featureDescription){
        return this.carFeaturesService.findCarFeaturesByFeatureDescription(featureDescription);
    }

    @GetMapping("/all")
    public Map<String, Object> findAllFeatures(Pagination pagination){
        List<CarFeaturesProjection> cfeat = this.carFeaturesService.findCarFeaturesProjectionAll(pagination);
        Map<String, Object> map = new HashMap<>();
        map.put("data", cfeat);
        map.put("pagination", pagination);
        return map;
    }

    @GetMapping
    public List<CarFeaturesProjection> findByFeatureDescription(){
        return this.carFeaturesService.findAllCarFeatures();
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
