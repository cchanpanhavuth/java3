package com.example.service.impl;

import com.example.configuration.exceptions.NotFoundException;
import com.example.entity.Car;
import com.example.entity.CarFeatures;
import com.example.entity.CarModel;
import com.example.entity.enums.StatusEnum;
import com.example.entity.projection.CarFeaturesProjection;
import com.example.entity.projection.CarModelProjection;
import com.example.entity.projection.CategoryProjection;
import com.example.entity.response.ApiStatus;
import com.example.entity.response.Pagination;
import com.example.repository.CarModelRepository;
import com.example.service.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.example.configuration.exceptions.TranscationException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;


import java.util.ArrayList;
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
        CarModel insertedModel = null;
        try{
            insertedModel = carModelRepository.save(carModel);
        }catch (Exception e){
            e.printStackTrace();
            throw new TranscationException(
                    ApiStatus.FAI_CREATED.getCode(),
                    ApiStatus.FAI_CREATED.getMessage());
        }
        return insertedModel;
    }
    @Transactional
    @Override
    public CarModel update(CarModel carModel) {
        CarModel modelToUpdate = carModelRepository.findById(carModel.getId())
                .orElseThrow(() -> new NotFoundException(
                        ApiStatus.NOT_FOUND.getCode(), ApiStatus.NOT_FOUND.getMessage()));
        try{
            if(!ObjectUtils.isEmpty(carModel)){
//                modelToUpdate.setModel(carModel.getModel());
            //    modelToUpdate.setName(carModel.getName());
//                List<Car> carList = new ArrayList<>();
//                if(!ObjectUtils.isEmpty(carModel.getCarModel())){
//                    for(Car car : carModel.getCarModel()){
//                        carList.add(car);
//                        car.setModel(modelToUpdate);
//                    }
//                    modelToUpdate.getCarModel().clear();
//                    modelToUpdate.getCarModel().addAll(carList);
//                }
                return carModelRepository.save(modelToUpdate);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new TranscationException(ApiStatus.FAI_UPDATED.getCode(), ApiStatus.FAI_UPDATED.getMessage());
        }
        return null;
    }
    @Override
    public boolean deleteById(Long id) {
        CarModel carModel = carModelRepository.findById(id).orElseThrow(() -> new NotFoundException(
                ApiStatus.NOT_FOUND.getCode(), ApiStatus.NOT_FOUND.getMessage()));
        if(!ObjectUtils.isEmpty(carModel)){
            carModelRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public CarModel findById(Long id) {
        return this.carModelRepository.findById(id).orElseThrow(() -> new NotFoundException(
                ApiStatus.NOT_FOUND.getCode(), ApiStatus.NOT_FOUND.getMessage()));
    }

//    @Override
//    public CarModelProjection findCarModelByName(String name) {
//        return this.carModelRepository.findCarModelByName(name).orElseThrow(
//                () -> new NotFoundException(
//                        ApiStatus.NOT_FOUND.getCode(),
//                        ApiStatus.NOT_FOUND.getMessage()
//                )
//        );
//    }

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

    @Override
    public CarModelProjection findModelProjectionById(Long id) {
        return this.carModelRepository.findCarModelProjectionById(id).orElseThrow(
                () -> new NotFoundException(
                        ApiStatus.NOT_FOUND.getCode(),
                        ApiStatus.NOT_FOUND.getMessage()
                )
        );
    }
    @Override
    public long countModelByStatus(StatusEnum statusEnum) {

        return this.carModelRepository.countCarModelByStatus(statusEnum);
    }

    @Override
    public List<CarModelProjection> findCarModelProjectionByModelContainingIgnoreCase(String model, Pagination pagination) {
        Page<CarModelProjection> modelProjections = this.carModelRepository.findCarModelProjectionByModelContainingIgnoreCase(
                model, PageRequest.of(pagination.getPage()-1, pagination.getSize())
        );
        pagination.setTotalCounts(modelProjections.getTotalElements());
        return modelProjections.getContent() ;
    }

}


