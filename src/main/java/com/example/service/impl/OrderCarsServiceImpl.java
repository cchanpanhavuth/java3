package com.example.service.impl;

import com.example.entity.OrderCars;
import com.example.repository.OrderCarsRepository;
import com.example.service.OrderCarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class OrderCarsServiceImpl implements OrderCarsService {
    private OrderCarsRepository orderCarsRepository;
    @Autowired
    public OrderCarsServiceImpl(OrderCarsRepository orderCarsRepository) {
        this.orderCarsRepository = orderCarsRepository;
    }

    @Override
    public OrderCars add(OrderCars orderCars) {
        return this.orderCarsRepository.save(orderCars);
    }

    @Override
    public OrderCars update(OrderCars orderCars) {
        OrderCars orderCars1 = this.orderCarsRepository.findById(orderCars.getId()).orElse(null);
        if (!ObjectUtils.isEmpty(orderCars)){
            orderCars1.setCustomer(orderCars.getCustomer());
            orderCars1.setSaleStaff(orderCars.getSaleStaff());
            orderCars1.setTotalAmount(orderCars.getTotalAmount());
            orderCars1.setDiscount(orderCars.getDiscount());
            return orderCarsRepository.save(orderCars1);
        }
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        OrderCars orderCars1 = this.orderCarsRepository.findById(id).orElse(null);
        if (orderCars1 == null){
            return false;
        }
        this.orderCarsRepository.deleteById(id);
        return true;
    }

    @Override
    public OrderCars findById(Long id) {

        return this.orderCarsRepository.findById(id).orElse(null);
    }
}
