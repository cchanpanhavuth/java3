package com.example.service.impl;

import com.example.configuration.exceptions.NotFoundException;
import com.example.configuration.exceptions.TranscationException;
import com.example.entity.Car;
import com.example.entity.CarFeatures;
import com.example.entity.Category;
import com.example.entity.enums.StatusEnum;
import com.example.entity.projection.CarProjection;
import com.example.entity.projection.CategoryProjection;
import com.example.entity.response.ApiStatus;
import com.example.entity.response.Pagination;
import com.example.repository.CarRepository;
import com.example.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    @Override
    public Car add(Car car) {
        Car insertedCar = null;
        try{
            insertedCar = carRepository.save(car);
        }catch (Exception e){
            e.printStackTrace();
            throw new TranscationException(
                    ApiStatus.FAI_CREATED.getCode(),
                    ApiStatus.FAI_CREATED.getMessage());
        }
        return insertedCar;
    }
    @Transactional
    @Override
    public Car update(Car car) {
        Car carToUpdate = carRepository.findById(car.getId())
                .orElseThrow(() -> new NotFoundException(
                        ApiStatus.NOT_FOUND.getCode(), ApiStatus.NOT_FOUND.getMessage()));
        try{
            if(!ObjectUtils.isEmpty(car)){
                carToUpdate.setPrice(car.getPrice());
                carToUpdate.setColor(car.getColor());
                carToUpdate.setYear(car.getYear());
//                List<Car> carList = new ArrayList<>();
//                List<Customer> customerList = new ArrayList<>();
//                if(!ObjectUtils.isEmpty(carFeatures.getCars())){
//                    for(Car car : carFeatures.getCars()){
//                        carList.add(car);
//                        car.setColor(featureToUpdate);
//                    }
//                    featureToUpdate.getAddresses().clear();
//                    featureToUpdate.getAddresses().addAll(addressList);
//                }
//                else if(!ObjectUtils.isEmpty(user.getAddresses())){
//                    for(Address adr : user.getAddresses()){
//                        addressList.add(adr);
//                        adr.setUser(featureToUpdate);
//                    }
//                    featureToUpdate.getAddresses().clear();
//                    featureToUpdate.getAddresses().addAll(addressList);
//                }
                return carRepository.save(carToUpdate);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new TranscationException(ApiStatus.FAI_UPDATED.getCode(), ApiStatus.FAI_UPDATED.getMessage());
        }
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new NotFoundException(
                ApiStatus.NOT_FOUND.getCode(), ApiStatus.NOT_FOUND.getMessage()));
        if(!ObjectUtils.isEmpty(car)){
            carRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Car findById(Long id) {

        return this.carRepository.findById(id).orElseThrow(() -> new NotFoundException(
                ApiStatus.NOT_FOUND.getCode(), ApiStatus.NOT_FOUND.getMessage()));
    }

    @Override
    public CarProjection findByPrice(Double price) {
        return this.carRepository.findByPrice(price).orElseThrow(
                () -> new NotFoundException(
                        ApiStatus.NOT_FOUND.getCode(),
                        ApiStatus.NOT_FOUND.getMessage()
                )
        );
    }

//    @Override
//    public List<CarProjection> findAllCar() {
//        return carRepository.findAllCarBy();
//    }

    @Override
    public List<CarProjection> findCarProjectionAll(Pagination pagination) {
        Page<CarProjection> car = carRepository.findAllCarProjectionBy(
                PageRequest.of(pagination.getPage()-1, pagination.getSize())
        );
        pagination.setTotalCounts(car.getTotalElements());
        return car.getContent() ;
    }

    @Override
    public CarProjection findCarProjectionById(Long id) {
        return this.carRepository.findCarProjectionById(id).orElseThrow(
                () -> new NotFoundException(
                        ApiStatus.NOT_FOUND.getCode(),
                        ApiStatus.NOT_FOUND.getMessage()
                )
        );
    }
    @Override
    public long countCarByStatus(StatusEnum statusEnum) {

        return this.carRepository.countCarByStatus(statusEnum);
    }

    @Override
    public List<CarProjection> findCarProjectionByYearContainingIgnoreCase(String year, Pagination pagination) {
        Page<CarProjection> carProjections = this.carRepository.findCarProjectionByYearContainingIgnoreCase(
                year, PageRequest.of(pagination.getPage()-1, pagination.getSize())
        );
        pagination.setTotalCounts(carProjections.getTotalElements());
        return carProjections.getContent() ;
    }
}
