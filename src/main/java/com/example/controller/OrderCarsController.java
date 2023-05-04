package com.example.controller;

import com.example.entity.Customer;
import com.example.entity.Inventory;
import com.example.entity.OrderCars;
import com.example.entity.SaleStaff;
import com.example.entity.projection.InventoryProjection;
import com.example.entity.projection.OrderCarDetailProjection;
import com.example.entity.projection.OrderCarProjection;
import com.example.entity.request.InventoryReq;
import com.example.entity.request.InventoryUpdateReq;
import com.example.entity.request.OrderCarReq;
import com.example.entity.request.OrderCarsUpdate;
import com.example.entity.response.ApiResponse;
import com.example.entity.response.ApiStatus;
import com.example.entity.response.Pagination;
import com.example.service.CustomerService;
import com.example.service.OrderCarsService;
import com.example.service.SaleStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RestController
@RequestMapping("api/v1/orderCars")
public class OrderCarsController {
    private CustomerService customerService;
    private SaleStaffService saleStaffService;
    private OrderCarsService orderCarsService;
    @Autowired
    public OrderCarsController(CustomerService customerService, SaleStaffService saleStaffService, OrderCarsService orderCarsService) {
        this.customerService = customerService;
        this.saleStaffService = saleStaffService;
        this.orderCarsService = orderCarsService;
    }
    @PostMapping("")
    public ApiResponse addOrderCars(@Validated @RequestBody OrderCarReq req){
        OrderCars orderCars = orderCarsService.add(req);
        OrderCars orderCar = orderCarsService.findById(orderCars.getId());
        return new ApiResponse<>(
                ApiStatus.SUC_CREATED.getCode(),
                ApiStatus.SUC_CREATED.getMessage(),
                orderCar
        );
    }
    @GetMapping("/{id}")
    public ApiResponse getOrderCarsById(@PathVariable(name = "id") Long id){
        OrderCars orderCar = orderCarsService.findById(id);
        return new ApiResponse<>(
                ApiStatus.SUC_CREATED.getCode(),
                ApiStatus.SUC_CREATED.getMessage(),
                orderCar
        );
    }
    @GetMapping("/{id}")
    public ApiResponse getOrderCarByCustomerId(@PathVariable(name = "id") Long id){
        OrderCarProjection orderCarProjection = orderCarsService.findByCustomerId(id);
        return new ApiResponse<>(
                ApiStatus.SUC_CREATED.getCode(),
                ApiStatus.SUC_CREATED.getMessage(),
                orderCarProjection
        );
    }
    @GetMapping("/{id}")
    public ApiResponse getOrderCarByStaffId(@PathVariable(name = "id") Long id){
        OrderCarProjection orderCarProjection = orderCarsService.findByStaffId(id);
        return new ApiResponse<>(
                ApiStatus.SUC_CREATED.getCode(),
                ApiStatus.SUC_CREATED.getMessage(),
                orderCarProjection
        );
    }
    @GetMapping("")
    public Map<String, Object> listOrderCars(Pagination pagination){
        List<OrderCarProjection> orderCarProjection = orderCarsService.findAllOrderCarProjectionBy(pagination);
        Map<String, Object> map = new HashMap<>();
        map.put("data", orderCarProjection);
        map.put("pagination", pagination);
        return map;
    }
    @PutMapping("")
    public ApiResponse updateOrderCar(@RequestBody OrderCarsUpdate req){
        OrderCars orderCars = orderCarsService.update(req);
        OrderCars orderCar = orderCarsService.findById(req.getId());
        return new ApiResponse<>(
                ApiStatus.SUC_UPDATED.getCode(),
                ApiStatus.SUC_UPDATED.getMessage(),
                orderCar
        );
    }
    @DeleteMapping("/{id}")
    public ApiResponse deleteCustomer(@PathVariable(name = "id") Long id){
        Boolean isDeleted = orderCarsService.deleteById(id);
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
