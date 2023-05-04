package com.example.service.impl;

import com.example.entity.Customer;
import com.example.entity.OrderCars;
import com.example.entity.OrderDetail;
import com.example.entity.projection.CustomerProjection;
import com.example.entity.projection.OrderCarDetailProjection;
import com.example.entity.request.OrderCarDetailReq;
import com.example.entity.request.OrderDetailUpdate;
import com.example.entity.response.Pagination;
import com.example.repository.OrderDetailRepository;
import com.example.service.OrderDetailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Optional;

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
    public OrderDetail add(OrderCarDetailReq req) {
        OrderDetail orderDetail = new OrderDetail();
        BeanUtils.copyProperties(req, orderDetail);
        return orderDetailRepository.save(orderDetail);
    }

    @Override

    public OrderDetail update(OrderDetailUpdate req) {
        OrderDetail orderDetail = orderDetailRepository.findById(req.getId())
                .orElseThrow(() -> new ResourceAccessException("Customer could not found!!"));
        BeanUtils.copyProperties(req, orderDetail);
        return orderDetail;


    @Override
    public boolean deleteById(Long id) {
        OrderDetail orderDetail = orderDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("Customer could not found!!"));
        orderDetailRepository.delete(orderDetail);
        return true;
    }

    @Override
    public OrderDetail findById(Long id) {
        return orderDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("Customer could not found!!"));
    }
    @Override
    public OrderCarDetailProjection findByOrderId(Long id) {
        return orderDetailRepository.findByOrderId(id)
                .orElseThrow(() -> new ResourceAccessException("Customer could not found!!"));
    }

    @Override
    public OrderCarDetailProjection findByCarId(Long id) {
        return orderDetailRepository.findByCarId(id)
                .orElseThrow(() -> new ResourceAccessException("Customer could not found!!"));
    }

    @Override
    public List<OrderCarDetailProjection> findAllOrderDetailProjectionBy(Pagination pagination) {
        Page<OrderCarDetailProjection> orderCarDetailProjections = orderDetailRepository.findAllOrderDetailProjectionBy(
                PageRequest.of(pagination.getPage()-1, pagination.getSize()));

        pagination.setTotalCounts(orderCarDetailProjections.getTotalElements());
        return orderCarDetailProjections.getContent();
    }
}
