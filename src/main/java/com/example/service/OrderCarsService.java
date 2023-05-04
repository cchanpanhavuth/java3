package com.example.service;

import com.example.entity.OrderCars;
import com.example.entity.OrderDetail;
import com.example.entity.projection.OrderCarProjection;
import com.example.entity.request.OrderCarReq;
import com.example.entity.request.OrderCarsUpdate;
import com.example.entity.response.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface OrderCarsService {
    OrderCars add(OrderCarReq req);
    OrderCars update(OrderCarsUpdate req);
    boolean deleteById(Long id);
    OrderCars findById(Long id);
    OrderCarProjection findByCustomerId(Long id);
    OrderCarProjection findByStaffId(Long id);
    List<OrderCarProjection> findAllOrderCarProjectionBy(Pagination pagination);
}
