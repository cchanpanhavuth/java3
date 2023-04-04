package com.example.service.impl;

import com.example.entity.OrderDetail;
import com.example.repository.OrderDetailRepository;
import com.example.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    private OrderDetailRepository orderDetailRepository;

    @Override
    public OrderDetail add(OrderDetail orderDetail) {
        return null;
    }

    @Override
    public OrderDetail update(OrderDetail orderDetail) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public OrderDetail findById(Long id) {
        return null;
    }

    public OrderDetailServiceImpl() {
    }
    @Autowired
    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }
}
