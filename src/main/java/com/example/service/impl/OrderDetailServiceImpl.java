package com.example.service.impl;

import com.example.entity.OrderCars;
import com.example.entity.OrderDetail;
import com.example.repository.OrderDetailRepository;
import com.example.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    private OrderDetailRepository orderDetailRepository;
    public OrderDetailServiceImpl() {
    }
    @Autowired
    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public OrderDetail add(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public OrderDetail update(OrderDetail orderDetail) {
        OrderDetail updateOrderDetail = this.orderDetailRepository.findById(orderDetail.getId()).orElse(null);
        if (!ObjectUtils.isEmpty(orderDetail)){
            updateOrderDetail.setUnitPrice(orderDetail.getUnitPrice());
            updateOrderDetail.setQuantity(orderDetail.getQuantity());
        }
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        OrderDetail deleteOrderDetail = this.orderDetailRepository.findById(id).orElse(null);
        if (deleteOrderDetail == null){
            return false;
        }
        this.orderDetailRepository.deleteById(id);
        return true;
    }

    @Override
    public OrderDetail findById(Long id) {

        return this.orderDetailRepository.findById(id).orElse(null);
    }


}
