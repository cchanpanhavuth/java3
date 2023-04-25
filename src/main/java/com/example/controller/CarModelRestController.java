package com.example.controller;

import com.example.entity.CarModel;
import com.example.entity.projection.CarModelProjection;
import com.example.entity.response.Pagination;
import com.example.service.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/carModel")
public class CarModelRestController {

    private CarModelService CarmodelService;
    @Autowired
    public CarModelRestController(CarModelService CarModelService){
        this.CarmodelService = CarModelService;
    }

    @GetMapping("/name/{name}")
    public CarModelProjection findByName(@PathVariable String name){
        return this.CarmodelService.findCarModelByName(name);
    }

    @GetMapping("/all")
    public Map<String, Object> findByName(Pagination pagination){
        List<CarModelProjection> cate = this.CarmodelService.findCarModelProjectionAll(pagination);
        Map<String, Object> map = new HashMap<>();
        map.put("data", cate);
        map.put("pagination", pagination);
        return map;
    }

    @GetMapping
    public List<CarModelProjection> findByName(){
        return this.CarmodelService.findAllCarModel();
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
