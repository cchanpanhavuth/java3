package com.example.service.impl;

import com.example.entity.CarModel;
import com.example.entity.projection.CarModelProjection;
import com.example.entity.projection.CategoryProjection;
import com.example.entity.response.Pagination;
import com.example.repository.CarModelRepository;
import com.example.service.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarModelServiceImpl implements CarModelService {
    private CarModelRepository carModelRepository;

    @Autowired
    public CarModelServiceImpl(CarModelRepository carModelRepository){
        this.carModelRepository = carModelRepository;

    }

    @Override
    public CarModel add(CarModel carModel) {
        carModel.setCreatedBy("Admin");
        return this.carModelRepository.save(carModel);
    }
    @Override
    public CarModel update(CarModel carModel) {
        CarModel carmo = this.carModelRepository.findById(carModel.getId()).orElse(null);
        if(carmo == null){
            return null;
        }
        carmo.setUpdatedBy("Dara");
        carmo.setCarModel(carModel.getCarModel());
        return this.carModelRepository.save(carmo);
    }
    @Override
    public boolean deleteById(Long id) {
        CarModel carmo = this.carModelRepository.findById(id).orElse(null);
        if(carmo == null){
            return false;
        }
        this.carModelRepository.deleteById(id);
        return true;
    }

    @Override
    public CarModel findById(Long id) {
        return this.carModelRepository.findById(id).orElse(null);
    }

    @Override
    public CarModelProjection findCarModelByName(String name) {
        return this.carModelRepository.findCarModelByName(name).orElse(null);
    }

    @Override
    public List<CarModelProjection> findAllCarModel() {
        return carModelRepository.findAllCarModelBy();
    }

    @Override
    public List<CarModelProjection> findCarModelProjectionAll(Pagination pagination) {
        Page<CarModelProjection> carmo = carModelRepository.findAllCarModelProjectionBy(
                PageRequest.of(pagination.getPage()-1, pagination.getSize())
        );
        pagination.setTotalCounts(carmo.getTotalElements());
        return carmo.getContent() ;
    }

}


