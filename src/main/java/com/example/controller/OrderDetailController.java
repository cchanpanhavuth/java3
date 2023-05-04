package com.example.controller;

import com.example.entity.Customer;
import com.example.entity.OrderDetail;
import com.example.entity.projection.CustomerProjection;
import com.example.entity.projection.OrderCarDetailProjection;
import com.example.entity.request.CustomerReq;
import com.example.entity.request.CustomerUpdateReq;
import com.example.entity.request.OrderCarDetailReq;
import com.example.entity.request.OrderDetailUpdate;
import com.example.entity.response.ApiResponse;
import com.example.entity.response.ApiStatus;
import com.example.entity.response.Pagination;
import com.example.service.OrderCarsService;
import com.example.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RestController
@RequestMapping("/api/v1/orderDetail")
public class OrderDetailController {
    private OrderDetailService orderDetailService;
    private OrderCarsService orderCarsService;
    @Autowired
    public OrderDetailController(OrderDetailService orderDetailService, OrderCarsService orderCarsService) {
        this.orderDetailService = orderDetailService;
        this.orderCarsService = orderCarsService;
    }
    @PostMapping("")
    public ApiResponse addOrderDetail(@Validated @RequestBody OrderCarDetailReq req){
        OrderDetail orderDetail = orderDetailService.add(req);
        OrderDetail orderDetail1 = orderDetailService.findById(orderDetail.getId());
        return new ApiResponse<>(
                ApiStatus.SUC_CREATED.getCode(),
                ApiStatus.SUC_CREATED.getMessage(),
                orderDetail1
        );
    }
    @GetMapping("/{id}")
    public ApiResponse getOrderById(@PathVariable(name = "id") Long id){
        OrderDetail orderDetail = orderDetailService.findById(id);
        return new ApiResponse<>(
                ApiStatus.SUC_CREATED.getCode(),
                ApiStatus.SUC_CREATED.getMessage(),
                orderDetail
        );
    }
    @GetMapping("/{id}")
    public ApiResponse getCarById(@PathVariable(name = "id") Long id){
        OrderDetail orderDetail = orderDetailService.findById(id);
        return new ApiResponse<>(
                ApiStatus.SUC_CREATED.getCode(),
                ApiStatus.SUC_CREATED.getMessage(),
                orderDetail
        );
    }
    @GetMapping("")
    public Map<String, Object> listOrderDetail(Pagination pagination){
        List<OrderCarDetailProjection> orderCarDetailProjection = orderDetailService.findAllOrderDetailProjectionBy(pagination);
        Map<String, Object> map = new HashMap<>();
        map.put("data", orderCarDetailProjection);
        map.put("pagination", pagination);
        return map;
    }
    @PutMapping("")
    public ApiResponse updateCustomer(@RequestBody OrderDetailUpdate req){
        OrderDetail orderDetail = orderDetailService.update(req);
        OrderDetail orderDetail1 = orderDetailService.findById(req.getId());
        return new ApiResponse<>(
                ApiStatus.SUC_UPDATED.getCode(),
                ApiStatus.SUC_UPDATED.getMessage(),
                orderDetail1
        );
    }
    @DeleteMapping("/{id}")
    public ApiResponse deleteCustomer(@PathVariable(name = "id") Long id){
        Boolean isDeleted = orderDetailService.deleteById(id);
        if (!isDeleted){
            return new ApiResponse<>(
                    ApiStatus.FAI_DELETED.getCode(),
                    ApiStatus.FAI_DELETED.getMessage()
            );
        }
        return new ApiResponse<>(
                ApiStatus.SUC_DELETED.getCode(),
                ApiStatus.SUC_DELETED.getMessage()
        );
    }


}
