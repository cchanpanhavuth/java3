package com.example.service.impl;

import com.example.entity.CarFeatures;
import com.example.repository.CarFeaturesRepository;
import com.example.service.CarFeaturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
