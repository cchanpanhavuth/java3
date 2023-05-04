package com.example.controller;

import com.example.entity.Customer;
import com.example.entity.projection.CustomerProjection;
import com.example.entity.request.CustomerReq;
import com.example.entity.request.CustomerUpdateReq;
import com.example.entity.response.ApiResponse;
import com.example.entity.response.ApiStatus;
import com.example.entity.response.Pagination;
import com.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @PostMapping("")
    public ApiResponse addCustomer(@Validated @RequestBody CustomerReq req){
        Customer customer = customerService.add(req);
        CustomerProjection customerProjection = customerService.findCustomerById(customer.getId());
        return new ApiResponse<>(
                ApiStatus.SUC_CREATED.getCode(),
                ApiStatus.SUC_CREATED.getMessage(),
                customerProjection
        );
    }
    @GetMapping("/{id}")
    public ApiResponse getCustomerProjectionById(@PathVariable(name = "id") Long id){
        CustomerProjection customerProjection = customerService.findCustomerById(id);
        return new ApiResponse<>(
                ApiStatus.SUC_CREATED.getCode(),
                ApiStatus.SUC_CREATED.getMessage(),
                customerProjection
        );
    }
    @GetMapping("/{id}")
    public ApiResponse getCustomerById(@PathVariable(name = "id") Long id){
        Customer customer = customerService.findById(id);
        return new ApiResponse<>(
                ApiStatus.SUC_CREATED.getCode(),
                ApiStatus.SUC_CREATED.getMessage(),
                customer
        );
    }
    @GetMapping("")
    public Map<String, Object> listCustomer(Pagination pagination){
        List<CustomerProjection> customerProjections = customerService.findAllCustomerProjectionBy(pagination);
        Map<String, Object> map = new HashMap<>();
        map.put("data", customerProjections);
        map.put("pagination", pagination);
        return map;
    }
    @PutMapping("")
    public ApiResponse updateCustomer(@RequestBody CustomerUpdateReq req){
        Customer customer = customerService.update(req);
        CustomerProjection customerProjection = customerService.findCustomerById(customer.getId());
        return new ApiResponse<>(
                ApiStatus.SUC_UPDATED.getCode(),
                ApiStatus.SUC_UPDATED.getMessage(),
                customerProjection
        );
    }
    @DeleteMapping("/{id}")
    public ApiResponse deleteCustomer(@PathVariable(name = "id") Long id){
        Boolean isDeleted = customerService.deleteById(id);
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
