package com.example.service.impl;

import com.example.entity.CarFeatures;
import com.example.entity.projection.CarFeaturesProjection;
import com.example.entity.response.Pagination;
import com.example.repository.CarFeaturesRepository;
import com.example.service.CarFeaturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarFeaturesServiceImpl implements CarFeaturesService {
    private CarFeaturesRepository carFeaturesRepository;

    @Autowired
    public CarFeaturesServiceImpl(CarFeaturesRepository carFeaturesRepository){
        this.carFeaturesRepository = carFeaturesRepository;

    }

    @Override
    public CarFeatures add(CarFeatures carFeatures) {
        carFeatures.setCreatedBy("Admin");
        return this.carFeaturesRepository.save(carFeatures);
    }
    @Override
    public CarFeatures update(CarFeatures carFeatures) {
        CarFeatures carfeat = this.carFeaturesRepository.findById(carFeatures.getId()).orElse(null);
        if(carfeat == null){
            return null;
        }
        carfeat.setUpdatedBy("Dara");
        carfeat.setFeatureDescription(carfeat.getFeatureDescription());
        carfeat.setCars(carfeat.getCars());
        carfeat.setCustomers(carfeat.getCustomers());
        return this.carFeaturesRepository.save(carfeat);
    }
    @Override
    public boolean deleteById(Long id) {
        CarFeatures carFeatures = this.carFeaturesRepository.findById(id).orElse(null);
        if(carFeatures == null){
            return false;
        }
        this.carFeaturesRepository.deleteById(id);
        return true;
    }

    @Override
    public CarFeatures findById(Long id) {
        return this.carFeaturesRepository.findById(id).orElse(null);
    }

    @Override
    public CarFeaturesProjection findCarFeaturesByFeatureDescription(String featureDescription) {
        return this.carFeaturesRepository.findCarFeaturesByFeatureDescription(featureDescription).orElse(null);
    }

    @Override
    public List<CarFeaturesProjection> findAllCarFeatures() {
        return carFeaturesRepository.findAllCarFeaturesBy();
    }

    @Override
    public List<CarFeaturesProjection> findCarFeaturesProjectionAll(Pagination pagination) {
        Page<CarFeaturesProjection> carmo = carFeaturesRepository.findAllCarFeaturesProjectionBy(
                PageRequest.of(pagination.getPage()-1, pagination.getSize())
        );
        pagination.setTotalCounts(carmo.getTotalElements());
        return carmo.getContent() ;
    }

}
