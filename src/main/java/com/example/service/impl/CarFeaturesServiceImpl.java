package com.example.service.impl;

import com.example.configuration.exceptions.NotFoundException;
import com.example.configuration.exceptions.TranscationException;
import com.example.entity.Car;
import com.example.entity.CarFeatures;
import com.example.entity.Customer;
import com.example.entity.User;
import com.example.entity.enums.StatusEnum;
import com.example.entity.projection.CarFeaturesProjection;
import com.example.entity.response.ApiStatus;
import com.example.entity.response.Pagination;
import com.example.repository.CarFeaturesRepository;
import com.example.service.CarFeaturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
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
        CarFeatures insertedFeature = null;
        try{
            insertedFeature = carFeaturesRepository.save(carFeatures);
        }catch (Exception e){
            e.printStackTrace();
            throw new TranscationException(
                    ApiStatus.FAI_CREATED.getCode(),
                    ApiStatus.FAI_CREATED.getMessage());
        }
        return insertedFeature;
    }

    @Transactional
    @Override
    public CarFeatures update(CarFeatures carFeatures) {
        CarFeatures featureToUpdate = carFeaturesRepository.findById(carFeatures.getId())
                .orElseThrow(() -> new NotFoundException(
                        ApiStatus.NOT_FOUND.getCode(), ApiStatus.NOT_FOUND.getMessage()));
        try{
            if(!ObjectUtils.isEmpty(carFeatures)){
                featureToUpdate.setFeatureDescription(carFeatures.getFeatureDescription());
                return carFeaturesRepository.save(featureToUpdate);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new TranscationException(ApiStatus.FAI_UPDATED.getCode(), ApiStatus.FAI_UPDATED.getMessage());
        }
        return null;
    }
    @Override
    public boolean deleteById(Long id) {
        CarFeatures carFeatures = carFeaturesRepository.findById(id).orElseThrow(() -> new NotFoundException(
                ApiStatus.NOT_FOUND.getCode(), ApiStatus.NOT_FOUND.getMessage()));
        if(!ObjectUtils.isEmpty(carFeatures)){
            carFeaturesRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public CarFeatures findById(Long id) {
        return this.carFeaturesRepository.findById(id).orElseThrow(() -> new NotFoundException(
            ApiStatus.NOT_FOUND.getCode(), ApiStatus.NOT_FOUND.getMessage()));
    }


    @Override
    public CarFeaturesProjection findCarFeaturesByFeatureDescription(String featureDescription) {
        return this.carFeaturesRepository.findCarFeaturesByFeatureDescription(featureDescription).orElseThrow(
                () -> new NotFoundException(
                        ApiStatus.NOT_FOUND.getCode(),
                        ApiStatus.NOT_FOUND.getMessage()
                )
        );
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

    @Override
    public CarFeaturesProjection findFeatureProjectionById(Long id) {
        return this.carFeaturesRepository.findCarFeatureProjectionById(id).orElseThrow(
                () -> new NotFoundException(
                        ApiStatus.NOT_FOUND.getCode(),
                        ApiStatus.NOT_FOUND.getMessage()
                )
        );
    }
    @Override
    public long countFeatureByStatus(StatusEnum statusEnum) {

        return this.carFeaturesRepository.countCarFeaturesByStatus(statusEnum);
    }

    @Override
    public List<CarFeaturesProjection> findCarFeaturesProjectionByFeatureDescriptionContainingIgnoreCase(String featureDescription, Pagination pagination) {
        Page<CarFeaturesProjection> userProjections = this.carFeaturesRepository.findCarFeaturesProjectionByFeatureDescriptionContainingIgnoreCase(
                featureDescription, PageRequest.of(pagination.getPage()-1, pagination.getSize())
        );
        pagination.setTotalCounts(userProjections.getTotalElements());
        return userProjections.getContent() ;
    }


}
