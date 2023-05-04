package com.example.service;

import com.example.entity.OrderDetail;
import com.example.entity.projection.OrderCarDetailProjection;
import com.example.entity.request.OrderCarDetailReq;
import com.example.entity.request.OrderDetailUpdate;
import com.example.entity.response.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface OrderDetailService {
    OrderDetail add(OrderCarDetailReq req);
    OrderDetail update(OrderDetailUpdate req);
    boolean deleteById(Long id);
    OrderDetail findById(Long id);
    OrderCarDetailProjection findByOrderId(Long id);
    OrderCarDetailProjection findByCarId(Long id);
    List<OrderCarDetailProjection> findAllOrderDetailProjectionBy(Pagination pagination);
}
