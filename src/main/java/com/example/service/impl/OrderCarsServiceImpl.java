package com.example.service.impl;

import com.example.entity.Customer;
import com.example.entity.OrderCars;
import com.example.entity.projection.CustomerProjection;
import com.example.entity.projection.OrderCarProjection;
import com.example.entity.request.OrderCarReq;
import com.example.entity.request.OrderCarsUpdate;
import com.example.entity.response.Pagination;
import com.example.repository.OrderCarsRepository;
import com.example.service.OrderCarsService;
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
public class OrderCarsServiceImpl implements OrderCarsService {
    private OrderCarsRepository orderCarsRepository;
    @Autowired
    public OrderCarsServiceImpl(OrderCarsRepository orderCarsRepository) {
        this.orderCarsRepository = orderCarsRepository;
    }

    @Override
    public OrderCars add(OrderCarReq req) {
        OrderCars orderCar = new OrderCars();
        BeanUtils.copyProperties(req, orderCar);
        return orderCarsRepository.save(orderCar);
    }

    @Override
    public OrderCars update(OrderCarsUpdate req) {
        OrderCars orderCar = orderCarsRepository.findById(req.getId())
                .orElseThrow(() -> new ResourceAccessException("Customer could not found!!"));
        BeanUtils.copyProperties(req, orderCar);
        return orderCar;
    }

    @Override
    public boolean deleteById(Long id) {
        OrderCars orderCar = orderCarsRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("Customer could not found!!"));
        orderCarsRepository.delete(orderCar);
        return true;
    }

    @Override
    public OrderCars findById(Long id) {
        return orderCarsRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("Customer could not found!!"));
    }

    @Override
    public OrderCarProjection findByCustomerId(Long id) {
        return orderCarsRepository.findByCustomerId(id)
                .orElseThrow(() -> new ResourceAccessException("Customer could not found!!"));
    }

    @Override
    public OrderCarProjection findByStaffId(Long id) {
        return orderCarsRepository.findByStaffId(id)
                .orElseThrow(() -> new ResourceAccessException("Customer could not found!!"));
    }

    @Override
    public List<OrderCarProjection> findAllOrderCarProjectionBy(Pagination pagination) {

        Page<OrderCarProjection> customerProjection = orderCarsRepository.findAllOrderCarProjectionBy(
                PageRequest.of(pagination.getPage()-1, pagination.getSize()));

        pagination.setTotalCounts(customerProjection.getTotalElements());
        return customerProjection.getContent();

    }
}
