package com.example.service;

import com.example.entity.OrderCars;
import com.example.entity.OrderDetail;

public interface OrderCarsService {
    OrderCars add(OrderCars orderCars);
    OrderCars update(OrderCars orderCars);
    boolean deleteById(Long id);
    OrderCars findById(Long id);
}
