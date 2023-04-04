package com.example.service;

import com.example.entity.OrderDetail;

public interface OrderDetailService {
    OrderDetail add(OrderDetail orderDetail);
    OrderDetail update(OrderDetail orderDetail);
    boolean deleteById(Long id);
    OrderDetail findById(Long id);
}
